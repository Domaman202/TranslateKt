package ru.DmN.translate

/**
 * Ошибка поиска перевода.
 *
 * @param message Сообщение.
 */
class TranslateNotFound(message: String) : RuntimeException(message)