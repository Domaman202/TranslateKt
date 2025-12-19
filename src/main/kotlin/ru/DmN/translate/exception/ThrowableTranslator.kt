package ru.DmN.translate.exception

import ru.DmN.translate.Language

/**
 * Переводчик для [ITranslatedThrowable] производных исключений.
 *
 * @param T тип переводимого исключения.
 */
abstract class ThrowableTranslator <T> where T : Throwable, T : ITranslatedThrowable<T> {
    /**
     * Перевод.
     *
     * @param language Язык перевода.
     * @param throwable Исключение.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    open fun translate(language: Language, throwable: T): String =
        this.translateOrNull(language, throwable) ?: throw TranslationNotFoundException("Translate of '${throwable.javaClass}' not found")

    /**
     * Перевод.
     *
     * @param language Язык перевода.
     * @param throwable Исключение.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return `Перевод` - если найден, `null` - иначе.
     */
    @Throws(TranslationNotFoundException::class)
    abstract fun translateOrNull(language: Language, throwable: T): String?
}