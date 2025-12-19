package ru.DmN.translate

import ru.DmN.translate.GlobalTranslateProvider.RESOLVERS
import ru.DmN.translate.GlobalTranslateProvider.TRANSLATE


/**
 * Глобальный поставщик переводов.
 *
 * @property RESOLVERS Запросчики переводов.
 * @property TRANSLATE Список загруженных переводов.
 */
object GlobalTranslateProvider : TranslateProvider() {
    val RESOLVERS: MutableList<TranslateResolver> = ArrayList()
    val TRANSLATE: MutableMap<Language, MutableMap<TranslateKey, String>> = HashMap()

    override fun translateNoFmtOrNull(language: Language, key: TranslateKey): String? {
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
        fun resolve(language: Language): Map<TranslateKey, String>?
    }
}