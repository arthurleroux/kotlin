package fr.iim.iwm.a5.leroux.arthur.controllers

interface CommentController {
    fun  insertComment(article_id: Int, text: String): Any
    fun  deleteComment(id: Int): Any
}
