package ru.DmN.translate.exception

import ru.DmN.translate.Language

/**
 * [java.lang.Throwable] производное исключение имеющее перевод.
 */
interface ITranslatedThrowable<in T> where T : Throwable, T : ITranslatedThrowable<T> {
    /**
     * Перевод.
     *
     * @param language Язык перевода.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    fun translate(language: Language): String =
        this.translateOrNull(language) ?: throw TranslationNotFoundException("Translate of '${this.javaClass}' not found")

    /**
     * Перевод.
     *
     * @param language Язык перевода.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return `Перевод` - если найден, `null` - иначе.
     */
    @Throws(TranslationNotFoundException::class)
    fun translateOrNull(language: Language): String? =
        @Suppress("UNCHECKED_CAST")
        this.translator.translateOrNull(language, this as T)

    /**
     * Поставщик перевода для данного исключения.
     */
    val translator: ThrowableTranslator<T>
}