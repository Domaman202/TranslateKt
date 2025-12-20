package ru.DmN.translate.exception

import ru.DmN.translate.Language

/**
 * [java.lang.Throwable] производное исключение имеющее перевод.
 */
interface ITranslatedThrowable<in T> where T : Throwable, T : ITranslatedThrowable<T> {
    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    fun translate(language: Language): String =
        @Suppress("UNCHECKED_CAST")
        this.translator.translate(language, this as T)

    /**
     * Поставщик перевода для данного исключения.
     */
    val translator: ThrowableTranslator<T>
}