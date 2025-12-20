package ru.DmN.translate.exception

import ru.DmN.translate.Language
import ru.DmN.translate.TranslationKey
import ru.DmN.translate.provider.TranslateProvider
import java.lang.reflect.Modifier

open class ReflectiveThrowableTranslator<T>(val provider: TranslateProvider) : ThrowableTranslator<T>() where T : Throwable {
    override fun translateOrNull(language: Language, throwable: T): String? {
        val fields = throwable.javaClass.declaredFields.filter { !Modifier.isStatic(it.modifiers) }
        val args = arrayOfNulls<Pair<String, Any?>>(fields.size)
        for (index in fields.indices) {
            val field = fields[index]
            field.isAccessible = true
            args[index] = field.name to field.get(throwable)
        }

        @Suppress("UNCHECKED_CAST")
        return this.provider.translateFmtOrNull(
            language,
            TranslationKey(throwable.javaClass.name),
            *args as Array<out Pair<String, Any?>>
        )
    }
}