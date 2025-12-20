package ru.DmN.translate.exception

import ru.DmN.translate.Language

/**
 * Переводчик для [ITranslatedThrowable] производных исключений.
 *
 * @param T тип переводимого исключения.
 */
abstract class ThrowableTranslator<in T> where T : Throwable {
    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param throwable Исключение.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    abstract fun translate(language: Language, throwable: T): String
}