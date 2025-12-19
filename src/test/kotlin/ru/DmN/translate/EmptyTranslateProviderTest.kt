package ru.DmN.translate

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language.Companion.ENGLISH
import kotlin.test.Test
import kotlin.test.assertNull

class EmptyTranslateProviderTest {
    object TestProvider : EmptyTranslateProvider()

    @Test
    @DisplayName("Перевод или null без форматирования")
    fun translateNoFmtOrNullTest() {
        assertNull(TestProvider.translateNoFmtOrNull(ENGLISH, TranslateKey("nullable")))
    }


    @Test
    @DisplayName("Перевод без форматирования")
    fun translateNoFmtTest() {
        assertThrows<TranslateNotFound> {
            TestProvider.translateNoFmt(ENGLISH, TranslateKey("notFound"))
        }
    }

    @Test
    @DisplayName("Перевод или null с форматированием")
    fun translateFmtOrNullTest() {
        assertNull(TestProvider.translateFmtOrNull(ENGLISH, TranslateKey("nullable")))
    }


    @Test
    @DisplayName("Перевод с форматированием")
    fun translateFmtTest() {
        assertThrows<TranslateNotFound> {
            TestProvider.translateFmt(ENGLISH, TranslateKey("notFound"))
        }
    }
}