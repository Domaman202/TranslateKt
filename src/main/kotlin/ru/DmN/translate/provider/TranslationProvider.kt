package ru.DmN.translate.provider

import ru.DmN.cmd.style.FmtException
import ru.DmN.cmd.style.FmtUtils.fmt
import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.exception.TranslationNotFoundException

/**
 * Поставщик переводов.
 */
abstract class TranslationProvider {
    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @param args Аргументы форматирования.
     * @return Перевод.
     * @throws TranslationNotFoundException Перевод не найден.
     * @throws FmtException Ошибка форматирования.
     */
    @Throws(TranslationNotFoundException::class, FmtException::class)
    open fun translate(language: Language, key: TranslationKey, vararg args: Pair<String, Any?>): String =
        this.translateOrNull(language, key, *args)
            ?: GlobalTranslationProvider.translateOrNull(language, key, *args)
            ?: throw TranslationNotFoundException("Translate of '${key.key}' not found")

    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @param args Аргументы форматирования.
     * @return `Перевод` - если есть, `null` - иначе.
     * @throws FmtException Ошибка форматирования.
     */
    @Throws(FmtException::class)
    open fun translateOrNull(language: Language, key: TranslationKey, vararg args: Pair<String, Any?>): String? =
        this.translateNoFmtOrNull(language, key)?.fmt(*args)

    /**
     * Перевод без форматирования.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Неотформатированный перевод.
     */
    @Throws(TranslationNotFoundException::class)
    open fun translateNoFmt(language: Language, key: TranslationKey): String =
        this.translateNoFmtOrNull(language, key)
            ?: GlobalTranslationProvider.translateNoFmtOrNull(language, key)
            ?: throw TranslationNotFoundException("Translate of '${key.key}' not found")

    /**
     * Перевод без форматирования.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @return `Неотформатированный перевод` - если есть, `null` - иначе.
     */
    abstract fun translateNoFmtOrNull(language: Language, key: TranslationKey): String?
}