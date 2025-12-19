package ru.DmN.translate

import java.util.Locale

/**
 * Язык для перевода.
 *
 * @param code Код языка.
 */
@JvmInline
value class Language(val code: String) {
    companion object {
        val ENGLISH = of(Locale.ENGLISH)

        fun of(locale: Locale): Language =
            Language(locale.language)
    }
}