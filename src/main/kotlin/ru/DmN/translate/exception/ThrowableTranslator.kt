package ru.DmN.translate.exception

import ru.DmN.cmd.style.FmtException
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
     * @return Перевод.
     * @throws TranslationNotFoundException Перевод не найден.
     * @throws FmtException Ошибка форматирования.
     */
    @Throws(TranslationNotFoundException::class, FmtException::class)
    abstract fun translate(language: Language, throwable: T): String
}