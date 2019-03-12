package fr.iim.iwm.a5.leroux.arthur.templates

import fr.iim.iwm.a5.leroux.arthur.data.Article
import kotlinx.html.*

fun HTML.indexTemplate(articles: List<Article>) {
    head{
        title("liste des article")
    }

    body {
        articles.forEach{
            p {
                a(href = "/article/${it.id}") {
                    +it.title
                }
            }
        }
    }
}