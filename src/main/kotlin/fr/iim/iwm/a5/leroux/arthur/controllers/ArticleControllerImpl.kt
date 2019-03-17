package fr.iim.iwm.a5.leroux.arthur.controllers

import fr.iim.iwm.a5.leroux.arthur.models.Model
import fr.iim.iwm.a5.leroux.arthur.templates.articleTemplate
import fr.iim.iwm.a5.leroux.arthur.templates.adminArticleTemplate
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) : ArticleController {

    override fun  startHD(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getComments(id)
        if (article !== null) {
            return  HtmlContent { articleTemplate(article, comments)}
        }
        return HttpStatusCode.NotFound
    }

    override fun  adminStartHD(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getComments(id)
        if (article !== null) {
            return  HtmlContent { adminArticleTemplate(article, comments)}
        }
        return HttpStatusCode.NotFound
    }

    override fun insertArticle(title: String, text: String) {
        model.insertArticle(title, text)
    }

    override fun deleteArticle(id: Int): Any {
        model.deleteArticle(id)
        return true
    }
}