package ru.DmN.translate.provider

import kotlinx.serialization.json.Json
import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey

/**
 * Поставщик переводов с подгрузкой из [Class.getResource].
 */
open class ResourceTranslateProvider(val path: String) : TranslateProvider() {
    private val cache: MutableMap<Language, MutableMap<TranslationKey, String>> = HashMap()

    override fun translateNoFmtOrNull(language: Language, key: TranslationKey): String? {
        this.cache[language]?.let { return it[key] }
        val json = this.javaClass.getResource("/$path/${language.code}.json") ?: return null
        this.cache[language] = Json.decodeFromString(json.readText(Charsets.UTF_8))
        return this.translateNoFmtOrNull(language, key)
    }
}