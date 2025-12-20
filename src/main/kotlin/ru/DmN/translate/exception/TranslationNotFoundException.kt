package ru.DmN.translate.exception

/**
 * Ошибка поиска перевода.
 *
 * @param message Сообщение.
 */
class TranslationNotFoundException(message: String) : RuntimeException(message)