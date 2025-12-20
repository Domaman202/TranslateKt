package ru.DmN.translate

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import ru.DmN.translate.Language.Companion.ENGLISH
import ru.DmN.translate.exception.TranslationNotFoundException
import ru.DmN.translate.provider.ResourceTranslationProvider
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslationPairTest {
    object TestProvider : ResourceTranslationProvider("ru/DmN/translate/test")

    @Test
    @DisplayName("Перевод")
    fun translateTest() {
        assertThrows<TranslationNotFoundException> {
            TranslationPair(TranslationKey("notFound"), TestProvider).translate(ENGLISH)
        }
        assertEquals("Translate with formatting\u001B[00m", TranslationPair(TranslationKey("fmt"), TestProvider).translate(ENGLISH))
    }
}