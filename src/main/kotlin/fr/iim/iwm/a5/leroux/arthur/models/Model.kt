package fr.iim.iwm.a5.leroux.arthur.models

import fr.iim.iwm.a5.leroux.arthur.data.Article
import fr.iim.iwm.a5.leroux.arthur.data.Comment

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun getComments(article_id: Int): List<Comment>
    fun insertComment(article_id: Int, text: String)
    fun insertArticle(title: String, text: String)
    fun deleteArticle(id: Int): Any
}