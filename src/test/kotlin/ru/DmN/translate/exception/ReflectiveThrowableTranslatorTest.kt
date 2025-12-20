package ru.DmN.translate.exception

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language
import ru.DmN.translate.provider.ResourceTranslationProvider
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ReflectiveThrowableTranslatorTest {
    object TestProvider : ResourceTranslationProvider("ru/DmN/translate/test")
    object TestTranslator : ReflectiveThrowableTranslator<Throwable>(TestProvider)

    class TestException(val value: Any?) : Exception(), ITranslatedThrowable<TestException> {
        override val translator: ThrowableTranslator<TestException>
            get() = TestTranslator
    }


    @Test
    @DisplayName("Перевод")
    fun translateTest() {
        val exception = TestException(321)
        assertThrows<TranslationNotFoundException> {
            exception.translator.translate(Language("ru"), exception)
        }
        val translate = exception.translator.translate(Language.ENGLISH, exception)
        assertNotNull(translate)
        assertEquals("Value of throwable is '321'\u001B[00m", translate)
    }
}