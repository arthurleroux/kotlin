package fr.iim.iwm.a5.leroux.arthur.templates

import fr.iim.iwm.a5.leroux.arthur.data.Article
import fr.iim.iwm.a5.leroux.arthur.data.Comment
import kotlinx.html.*

fun HTML.ArticleTemplate(article: Article, comments: List<Comment>) {
    head{
        title("liste des article")
    }

    body {
        h1{ +article.title}
        p{ +article.text!! }
        ul {
            comments.forEach{
                li { +it.text!! }
            }
        }
    }
}