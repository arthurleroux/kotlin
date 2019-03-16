package fr.iim.iwm.a5.leroux.arthur.templates

import fr.iim.iwm.a5.leroux.arthur.data.Article
import fr.iim.iwm.a5.leroux.arthur.data.Comment
import kotlinx.html.*

fun HTML.adminArticleTemplate(article: Article, comments: List<Comment>) {
    head{
        title(article.title)
    }

    body {
        h1{ +article.title}
        p{ +article.text!! }

        h2{+"Commentaires:"}
        ul {
            comments.forEach{
                li {
                    +it.text!!
                    span {+ " | " }
                    a(href = "/admin/comment/${article.id}/${it.id}/delete") {
                        text(" [supprimer]")
                    }
                }
            }
        }
    }
}