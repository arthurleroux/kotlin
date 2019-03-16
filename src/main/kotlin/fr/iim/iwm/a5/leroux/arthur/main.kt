package fr.iim.iwm.a5.leroux.arthur

import fr.iim.iwm.a5.leroux.arthur.controllers.*
import fr.iim.iwm.a5.leroux.arthur.models.MysqlModel
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.freemarker.FreeMarker
import io.ktor.http.content.PartData
import io.ktor.request.receiveMultipart
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class App

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleControllerImpl,
    commentController: CommentController
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    install(Authentication) {
        basic(name = "admin") {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == "arthur" && credentials.password == "leroux") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
    
    routing {

        authenticate("admin") {
            route("/admin") {
                get("/") {
                    val content = articleListController.startHD()
                    call.respond(content)
                }

            }
        }

        get("/") {
            val content = articleListController.startHD()
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startHD(id)
            call.respond(content)
        }

        post("/comments/add") {
            val requestBody = call.receiveParameters()
            val text = requestBody["text"]
            val article_id = requestBody["article_id"]

            val result = commentController.insertComment(article_id!!.toInt(), text.toString())

            call.respondRedirect("/article/$article_id")
        }


    }
}

fun main() {
    val model = MysqlModel("jdbc:mysql://0.0.0.0:3306/homestead", "root", "secret")

    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val commentController = CommentControllerImpl(model)

    embeddedServer(Netty, 8080) {
        cmsApp(articleListController, articleController, commentController)
    }.start(true)
}