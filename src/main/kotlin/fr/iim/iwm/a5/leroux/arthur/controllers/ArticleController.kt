package fr.iim.iwm.a5.leroux.arthur.controllers

interface ArticleController {
    fun  startHD(id: Int): Any
    fun  adminStartHD(id: Int): Any
    fun  insertArticle(title: String, text: String): Any
    fun  deleteArticle(id: Int): Any
}
