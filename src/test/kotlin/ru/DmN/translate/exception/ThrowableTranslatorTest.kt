package ru.DmN.translate.exception

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ThrowableTranslatorTest {
    class TestException(val value: Any?) : Exception(), ITranslatedThrowable<TestException> {
        override val translator: ThrowableTranslator<TestException>
            get() = TestExceptionTranslator
    }

    object TestExceptionTranslator : ThrowableTranslator<TestException>() {
        override fun translateOrNull(language: Language, throwable: TestException): String? {
            if (language == Language.ENGLISH)
                return "Value of '${throwable}' is '${throwable.value}' (instance of ${throwable.value?.javaClass?.simpleName})"
            return null
        }
    }

    @Test
    @DisplayName("Перевод или null")
    fun translateOrNullTest() {
        val exception = TestException(123)
        assertNull(exception.translator.translateOrNull(Language("ru"), exception))
        val translate = exception.translator.translateOrNull(Language.ENGLISH, exception)
        assertNotNull(translate)
        assertEquals($$"Value of 'ru.DmN.translate.exception.ThrowableTranslatorTest$TestException' is '123' (instance of Integer)", translate)
    }


    @Test
    @DisplayName("Перевод")
    fun translateTest() {
        val exception = TestException(123)
        assertThrows<TranslationNotFoundException> {
            exception.translator.translate(Language("ru"), exception)
        }
        val translate = exception.translator.translate(Language.ENGLISH, exception)
        assertNotNull(translate)
        assertEquals($$"Value of 'ru.DmN.translate.exception.ThrowableTranslatorTest$TestException' is '123' (instance of Integer)", translate)
    }
}