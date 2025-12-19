package ru.DmN.translate

/**
 * Пустой поставщик переводов.
 */
open class EmptyTranslateProvider : TranslateProvider() {
    override fun translateNoFmtOrNull(language: Language, key: TranslateKey): String? = null

    /**
     * Пустой поставщик переводов.
     */
    companion object : EmptyTranslateProvider()
}