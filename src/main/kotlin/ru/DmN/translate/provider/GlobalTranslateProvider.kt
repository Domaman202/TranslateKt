package ru.DmN.translate.provider

import ru.DmN.translate.provider.GlobalTranslateProvider.RESOLVERS
import ru.DmN.translate.provider.GlobalTranslateProvider.TRANSLATE
import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey


/**
 * Глобальный поставщик переводов.
 *
 * @property RESOLVERS Запросчики переводов.
 * @property TRANSLATE Список загруженных переводов.
 */
object GlobalTranslateProvider : TranslateProvider() {
    val RESOLVERS: MutableList<TranslateResolver> = ArrayList()
    val TRANSLATE: MutableMap<Language, MutableMap<TranslationKey, String>> = HashMap()

    override fun translateNoFmtOrNull(language: Language, key: TranslationKey): String? {
        val translates = TRANSLATE.getOrPut(language) { HashMap() }
        return translates.getOrPut(key) {
            RESOLVERS.forEach {
                val resolved = it.resolve(language) ?: return@forEach
                val translate = resolved[key] ?: return@forEach
                translates.putAll(resolved)
                return@getOrPut translate
            }
            return null
        }
    }

    /**
     * Запросчик переводов.
     */
    fun interface TranslateResolver {
        /**
         * Запрос перевода.
         *
         * @param language Необходимый язык.
         * @return `Перевод` - если есть, `null` - иначе.
         */
        fun resolve(language: Language): Map<TranslationKey, String>?
    }
}