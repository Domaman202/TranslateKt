package ru.DmN.translate.provider

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language.Companion.ENGLISH
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.exception.TranslationNotFoundException
import kotlin.test.Test
import kotlin.test.assertNull

class EmptyTranslateProviderTest {
    object TestProvider : EmptyTranslateProvider()

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
    fun translateFmtOrNullTest() {
        assertNull(TestProvider.translateFmtOrNull(ENGLISH, TranslationKey("nullable")))
    }


    @Test
    @DisplayName("Перевод с форматированием")
    fun translateFmtTest() {
        assertThrows<TranslationNotFoundException> {
            TestProvider.translateFmt(ENGLISH, TranslationKey("notFound"))
        }
    }
}