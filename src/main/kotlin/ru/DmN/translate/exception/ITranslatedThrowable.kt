package ru.DmN.translate.exception

/**
 * [java.lang.Throwable] производное исключение имеющее перевод.
 */
interface ITranslatedThrowable<in T> where T : Throwable, T : ITranslatedThrowable<T> {
    /**
     * Поставщик перевода для данного исключения.
     */
    val translator: ThrowableTranslator<T>
}