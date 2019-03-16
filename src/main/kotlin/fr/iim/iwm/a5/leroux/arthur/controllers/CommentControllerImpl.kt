package fr.iim.iwm.a5.leroux.arthur.controllers

import fr.iim.iwm.a5.leroux.arthur.models.Model


class CommentControllerImpl(private val model: Model) : CommentController {
    override fun insertComment(article_id: Int, text: String) {
        val comment = model.insertComment(article_id, text)
    }
}