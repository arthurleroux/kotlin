package fr.iim.iwm.a5.leroux.arthur.templates

import fr.iim.iwm.a5.leroux.arthur.data.Article
import kotlinx.html.*

fun HTML.indexTemplate(articles: List<Article>) {
    head{
        title("Liste des article")
    }

    body {
        h1{+"Liste des articles:"}
        articles.forEach{
            p {
                a(href = "/article/${it.id}") {
                    +it.title
                }
            }
        }
    }
}