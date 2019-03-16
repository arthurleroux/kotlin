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
                    val content = articleListController.adminStartHD()
                    call.respond(content)
                }

                route("/article") {
                    post("/add") {
                        val requestBody = call.receiveParameters()
                        val title = requestBody["title"]
                        val text = requestBody["text"]

                        articleController.insertArticle(title.toString(), text.toString())

                        call.respondRedirect("/admin")
                    }

                    get("/{id}") {
                        val id = call.parameters["id"]!!.toInt()
                        val content = articleController.adminStartHD(id)
                        call.respond(content)
                    }

                    get("/{id}/delete") {
                        val id = call.parameters["id"]!!.toInt()
                        articleController.deleteArticle(id)
                        call.respondRedirect("/admin")
                    }
                }

                get("/comment/{article_id}/{id}/delete") {
                    val article_id = call.parameters["article_id"]!!.toInt()
                    val id = call.parameters["id"]!!.toInt()
                    commentController.deleteComment(id)
                    call.respondRedirect("/admin/article/$article_id")
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

            commentController.insertComment(article_id!!.toInt(), text.toString())

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