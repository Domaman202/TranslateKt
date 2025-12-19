package ru.DmN.translate

import kotlinx.serialization.json.Json

open class ResourceTranslateProvider(val path: String) : TranslateProvider() {
    private val cache: MutableMap<Language, MutableMap<TranslateKey, String>> = HashMap()

    override fun translateNoFmtOrNull(language: Language, key: TranslateKey): String? {
        this.cache[language]?.let { return it[key] }
        val json = this.javaClass.getResource("/$path/${language.code}.json") ?: return null
        this.cache[language] = Json.decodeFromString(json.readText(Charsets.UTF_8))
        return this.translateNoFmtOrNull(language, key)
    }
}