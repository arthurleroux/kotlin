package fr.iim.iwm.a5.leroux.arthur.templates

import fr.iim.iwm.a5.leroux.arthur.data.Article
import kotlinx.html.*

fun HTML.adminTemplate(articles: List<Article>) {
    head{
        title("Admin")
    }

    body {
        h1{+"Liste des articles :"}
        articles.forEach{
            p {
                a(href = "/admin/article/${it.id}") {
                    +it.title
                }
                span {+ " | " }
                a(href = "/admin/article/${it.id}/delete") {
                    text(" [supprimer]")
                }
            }
        }

        h2{+"Ajouter un article :"}
        form(action = "/admin/article/add",
            encType = FormEncType.multipartFormData,
            method = FormMethod.post) {
            textInput { name = "title"; required = true ; placeholder = "Titre"}
            br
            textArea { name = "text"; required = true ; placeholder = "Texte"}
            br
            submitInput()
        }
    }
}