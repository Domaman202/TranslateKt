package ru.DmN.translate

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslationKeyTest {
    @Test
    @DisplayName("Ключ перевода от класса")
    fun ofTest() {
        assertEquals("ru.DmN.translate.TranslationKeyTest", TranslationKey.of<TranslationKeyTest>().key)
    }

    @Test
    @DisplayName("Ключ перевода от класса с категорией")
    fun ofCategoryTest() {
        assertEquals("ru.DmN.translate.TranslationKeyTest.something", TranslationKey.of<TranslationKeyTest>("something").key)
    }
}