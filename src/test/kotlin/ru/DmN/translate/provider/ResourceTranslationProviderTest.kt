package ru.DmN.translate.provider

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language.Companion.ENGLISH
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.exception.TranslationNotFoundException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ResourceTranslationProviderTest {
    object TestProvider : ResourceTranslationProvider("ru/DmN/translate/test")

    @Test
    @DisplayName("Перевод или null без форматирования")
    fun translateNoFmtOrNullTest() {
        assertNull(TestProvider.translateNoFmtOrNull(ENGLISH, TranslationKey("nullable")))
        val notNull = TestProvider.translateNoFmtOrNull(ENGLISH, TranslationKey("noFmt"))
        assertNotNull(notNull)
        assertEquals("Translate without formatting", notNull)
    }


    @Test
    @DisplayName("Перевод без форматирования")
    fun translateNoFmtTest() {
        assertThrows<TranslationNotFoundException> {
            TestProvider.translateNoFmt(ENGLISH, TranslationKey("notFound"))
        }
        val notNull = TestProvider.translateNoFmt(ENGLISH, TranslationKey("noFmt"))
        assertNotNull(notNull)
        assertEquals("Translate without formatting", notNull)
    }

    @Test
    @DisplayName("Перевод или null с пустым форматированием")
    fun translateOrNullTest() {
        assertNull(TestProvider.translateOrNull(ENGLISH, TranslationKey("nullable")))
        val fmt = TestProvider.translateOrNull(ENGLISH, TranslationKey("fmt"))
        assertNotNull(fmt)
        assertEquals("Translate with formatting\u001B[00m", fmt)
    }


    @Test
    @DisplayName("Перевод с пустым форматированием")
    fun translateTest() {
        assertThrows<TranslationNotFoundException> {
            TestProvider.translate(ENGLISH, TranslationKey("notFound"))
        }
        val fmt = TestProvider.translate(ENGLISH, TranslationKey("fmt"))
        assertNotNull(fmt)
        assertEquals("Translate with formatting\u001B[00m", fmt)
    }

    @Test
    @DisplayName("Перевод или null с цветом и стилем в форматировании")
    fun translateOrNullColorStyleTest() {
        val fmt = TestProvider.translateOrNull(ENGLISH, TranslationKey("fmtColorStyle"))
        assertNotNull(fmt)
        assertEquals("\u001B[32m\u001B[03mGreen & Italic\u001B[00m", fmt)
    }


    @Test
    @DisplayName("Перевод с цветом и стилем в форматировании")
    fun translateColorStyleTest() {
        val fmt = TestProvider.translate(ENGLISH, TranslationKey("fmtColorStyle"))
        assertNotNull(fmt)
        assertEquals("\u001B[32m\u001B[03mGreen & Italic\u001B[00m", fmt)
    }

    @Test
    @DisplayName("Перевод или null со значением в форматировании")
    fun translateOrNullValueTest() {
        val fmt = TestProvider.translateOrNull(ENGLISH, TranslationKey("fmtValue"), "i" to 202)
        assertNotNull(fmt)
        assertEquals("i = 202\u001B[00m", fmt)
    }

    @Test
    @DisplayName("Перевод со значением в форматировании")
    fun translateValueTest() {
        val fmt = TestProvider.translate(ENGLISH, TranslationKey("fmtValue"), "i" to 202)
        assertNotNull(fmt)
        assertEquals("i = 202\u001B[00m", fmt)
    }

    @Test
    @DisplayName("Перевод или null с цветом, стилем и значением в форматировании")
    fun translateOrNullValueColorStyleTest() {
        val fmt = TestProvider.translateOrNull(ENGLISH, TranslationKey("fmtColorStyleValue"), "i" to 777)
        assertNotNull(fmt)
        assertEquals("\u001B[01mi \u001B[00m= \u001B[03m777\u001B[00m", fmt)
    }

    @Test
    @DisplayName("Перевод с цветом, стилем и значением в форматировании")
    fun translateValueColorStyleTest() {
        val fmt = TestProvider.translate(ENGLISH, TranslationKey("fmtColorStyleValue"), "i" to 777)
        assertNotNull(fmt)
        assertEquals("\u001B[01mi \u001B[00m= \u001B[03m777\u001B[00m", fmt)
    }
}