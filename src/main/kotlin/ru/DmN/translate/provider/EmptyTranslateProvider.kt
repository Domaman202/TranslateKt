package ru.DmN.translate.provider

import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey

/**
 * Пустой поставщик переводов.
 */
open class EmptyTranslateProvider : TranslateProvider() {
    override fun translateNoFmtOrNull(language: Language, key: TranslationKey): String? = null

    /**
     * Пустой поставщик переводов.
     */
    companion object : EmptyTranslateProvider()
}