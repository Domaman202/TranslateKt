package ru.DmN.translate.provider

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language.Companion.ENGLISH
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.exception.TranslationNotFoundException
import kotlin.test.Test
import kotlin.test.assertNull

class EmptyTranslationProviderTest {
    object TestProvider : EmptyTranslationProvider()

    @Test
    @DisplayName("Перевод или null без форматирования")
    fun translateNoFmtOrNullTest() {
        assertNull(TestProvider.translateNoFmtOrNull(ENGLISH, TranslationKey("nullable")))
    }


    @Test
    @DisplayName("Перевод без форматирования")
    fun translateNoFmtTest() {
        assertThrows<TranslationNotFoundException> {
            TestProvider.translateNoFmt(ENGLISH, TranslationKey("notFound"))
        }
    }

    @Test
    @DisplayName("Перевод или null с форматированием")
    fun translateOrNullTest() {
        assertNull(TestProvider.translateOrNull(ENGLISH, TranslationKey("nullable")))
    }


    @Test
    @DisplayName("Перевод с форматированием")
    fun translateTest() {
        assertThrows<TranslationNotFoundException> {
            TestProvider.translate(ENGLISH, TranslationKey("notFound"))
        }
    }
}