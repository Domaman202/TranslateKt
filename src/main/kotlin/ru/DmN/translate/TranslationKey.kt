package ru.DmN.translate

import kotlinx.serialization.Serializable

/**
 * Ключ перевода.
 *
 * @param key Ключ перевода.
 */
@JvmInline
@Serializable
value class TranslationKey(val key: String) {
    companion object {
        inline fun <reified T> of(): TranslationKey =
            TranslationKey(T::class.java.name)
        inline fun <reified T> of(category: String): TranslationKey =
            TranslationKey("${T::class.java.name}.$category")
    }
}