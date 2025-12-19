package ru.DmN.translate

open class EmptyTranslateProvider : TranslateProvider() {
    override fun translateNoFmtOrNull(language: Language, key: TranslateKey): String? = null

    companion object : EmptyTranslateProvider()
}