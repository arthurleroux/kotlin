package fr.iim.iwm.a5.leroux.arthur.controllers

import fr.iim.iwm.a5.leroux.arthur.models.Model
import fr.iim.iwm.a5.leroux.arthur.templates.ArticleTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) : ArticleController {

    override fun  startFM(id: Int): Any {
        val article = model.getArticle(id)
        if (article !== null) {
            return  FreeMarkerContent("article.ftl", article)
        }
        return HttpStatusCode.NotFound
    }

    override fun  startHD(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getComments(id)
        if (article !== null) {
            return  HtmlContent { ArticleTemplate(article, comments)}
        }
        return HttpStatusCode.NotFound
    }
}