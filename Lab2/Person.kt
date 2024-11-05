open class Person(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val middleName: String? = null,
    val git: String? = null,
    val phone: String? = null,
    val telegram: String? = null,
    val email: String? = null
) {
    // Функция для формирования ФИО инициалов
    open fun getFullName(): String {
        val initials = "${firstName.firstOrNull() ?: ""}.${middleName?.firstOrNull() ?: ""}."
        return "$lastName $initials"
    }

    // Метод для получения основного контакта
    open fun getContactInfo(): String {
        return when {
            !phone.isNullOrBlank() -> "Телефон: $phone"
            !telegram.isNullOrBlank() -> "Телеграм: $telegram"
            !email.isNullOrBlank() -> "Почта: $email"
            else -> "Контактов нет"
        }
    }

    override fun toString(): String {
        return """
            ID: $id
            ФИО: ${getFullName()}
            Git: ${git ?: "не указан"}
            Контакт: ${getContactInfo()}
        """.trimIndent()
    }

    // Валидация для контактов
    companion object {
        fun isValidPhone(phone: String) = phone.matches(Regex("^\\+?[0-9]{11}\$"))
        fun isValidTelegram(telegram: String) = telegram.matches(Regex("^@[A-Za-z0-9_]{5,32}\$"))
        fun isValidEmail(email: String) = email.matches(Regex("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$"))
        fun isValidGit(git: String) = git.matches(Regex("""^https:\/\/(www\.)?(github\.com|gitlab\.com|bitbucket\.org)\/[a-zA-Z0-9\-_]+$"""))
    }
}
