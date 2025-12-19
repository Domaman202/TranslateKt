package ru.DmN.translate

import kotlinx.serialization.Serializable

/**
 * Ключ перевода.
 *
 * @param key Ключ перевода.
 */
@JvmInline
@Serializable
value class TranslationKey(val key: String)