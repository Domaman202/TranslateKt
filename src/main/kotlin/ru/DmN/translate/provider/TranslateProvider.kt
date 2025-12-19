package ru.DmN.translate.provider

import ru.DmN.cmd.style.FmtUtils.fmt
import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.exception.TranslationNotFoundException

/**
 * Поставщик переводов.
 */
abstract class TranslateProvider {
    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @param args Аргументы форматирования.
     * @throws TranslationNotFoundException Перевод не найден.
     * @return Перевод.
     */
    @Throws(TranslationNotFoundException::class)
    open fun translateFmt(language: Language, key: TranslationKey, vararg args: Pair<String, Any?>): String =
        this.translateFmtOrNull(language, key, *args)
            ?: GlobalTranslateProvider.translateFmtOrNull(language, key, *args)
            ?: throw TranslationNotFoundException("Translate of '$key' not found")

    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @param args Аргументы форматирования.
     * @return `Перевод` - если есть, `null` - иначе.
     */
    open fun translateFmtOrNull(language: Language, key: TranslationKey, vararg args: Pair<String, Any?>): String? {
        var translate = this.translateNoFmtOrNull(language, key) ?: return null
        for ((key, value) in args)
            translate = translate.replace("{$key}", value.toString())
        return translate.fmt
    }

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
            ?: GlobalTranslateProvider.translateNoFmtOrNull(language, key)
            ?: throw TranslationNotFoundException("Translate of '$key' not found")

    /**
     * Перевод без форматирования.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @return `Неотформатированный перевод` - если есть, `null` - иначе.
     */
    abstract fun translateNoFmtOrNull(language: Language, key: TranslationKey): String?
}