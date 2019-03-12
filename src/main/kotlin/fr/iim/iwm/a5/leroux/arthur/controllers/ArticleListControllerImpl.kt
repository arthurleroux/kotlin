package fr.iim.iwm.a5.leroux.arthur.controllers

import fr.iim.iwm.a5.leroux.arthur.models.Model
import fr.iim.iwm.a5.leroux.arthur.templates.indexTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode

class ArticleListControllerImpl(private val model: Model) : ArticleListController {

    override fun  startFM(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return  FreeMarkerContent("index.ftl", articles)
        }
        return HttpStatusCode.NotFound
    }

    override fun  startHD(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return  HtmlContent { indexTemplate(articles)}
        }
        return HttpStatusCode.NotFound
    }
}