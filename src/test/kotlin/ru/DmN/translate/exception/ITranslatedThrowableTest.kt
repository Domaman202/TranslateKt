package ru.DmN.translate.exception

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language
import ru.DmN.translate.exception.ThrowableTranslatorTest.TestException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ITranslatedThrowableTest {
    @Test
    @DisplayName("Получение translator")
    fun translatorTest() {
        assertNotNull(TestException(null).translator)
    }

    @Test
    @DisplayName("Перевод или null")
    fun translateOrNullTest() {
        val exception = TestException(123)
        assertNull(exception.translateOrNull(Language("ru")))
        val translate = exception.translateOrNull(Language.ENGLISH)
        kotlin.test.assertNotNull(translate)
        assertEquals($$"Value of 'ru.DmN.translate.exception.ThrowableTranslatorTest$TestException' is '123' (instance of Integer)", translate)
    }


    @Test
    @DisplayName("Перевод")
    fun translateTest() {
        val exception = TestException(321)
        assertThrows<TranslationNotFoundException> {
            exception.translate(Language("ru"))
        }
        val translate = exception.translate(Language.ENGLISH)
        kotlin.test.assertNotNull(translate)
        assertEquals($$"Value of 'ru.DmN.translate.exception.ThrowableTranslatorTest$TestException' is '321' (instance of Integer)", translate)
    }
}