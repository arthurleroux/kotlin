package fr.iim.iwm.a5.leroux.arthur

import org.junit.Test
import kotlin.test.assertEquals

fun mrize(name: String) = "Mr $name"

class ArticleTest {

    @Test
    fun test() {
        assertEquals("Mr Arthur", mrize("Arthur"))
    }
}