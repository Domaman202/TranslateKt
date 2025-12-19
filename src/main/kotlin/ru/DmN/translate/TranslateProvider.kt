package ru.DmN.translate

import ru.DmN.cmd.style.FmtUtils.fmt

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
     * @throws TranslateNotFound Перевод не найден.
     * @return Перевод.
     */
    open fun translateFmt(language: Language, key: TranslateKey, vararg args: Pair<String, Any?>): String =
        this.translateFmtOrNull(language, key, *args)
            ?: GlobalTranslateProvider.translateFmtOrNull(language, key, *args)
            ?: throw TranslateNotFound("Translate of '$key' not found")

    /**
     * Перевод с форматированием.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @param args Аргументы форматирования.
     * @return `Перевод` - если есть, `null` - иначе.
     */
    open fun translateFmtOrNull(language: Language, key: TranslateKey, vararg args: Pair<String, Any?>): String? {
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
     * @throws TranslateNotFound Перевод не найден.
     * @return Неотформатированный перевод.
     */
    open fun translateNoFmt(language: Language, key: TranslateKey): String =
        this.translateNoFmtOrNull(language, key)
            ?: GlobalTranslateProvider.translateNoFmtOrNull(language, key)
            ?: throw TranslateNotFound("Translate of '$key' not found")

    /**
     * Перевод без форматирования.
     *
     * @param language Язык перевода.
     * @param key Ключ перевода.
     * @return `Неотформатированный перевод` - если есть, `null` - иначе.
     */
    abstract fun translateNoFmtOrNull(language: Language, key: TranslateKey): String?
}