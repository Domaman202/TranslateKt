package ru.DmN.translate

import ru.DmN.translate.exception.TranslationNotFoundException
import ru.DmN.translate.provider.TranslationProvider

/**
 * Пара из [TranslationKey] & [TranslationProvider].
 * При переводе не использует аргументы.
 */
data class TranslationPair(val key: TranslationKey, val provider: TranslationProvider) {
    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    fun translate(language: Language): String =
        this.provider.translate(language, this.key)
}