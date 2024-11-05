class Student(
    var lastName: String,
    var firstName: String,
    var middleName: String,
    var id: Int? = null,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
) {
    var phone: String? = phone
        set(value) {
            require(value == null || phoneValid(value)) { "Неверный формат телефона: $value" }
            field = value
        }

    var telegram: String? = telegram
        set(value) {
            require(value == null || telegramValid(value)) { "Неверный формат Telegram: $value" }
            field = value
        }

    var email: String? = email
        set(value) {
            require(value == null || emailValid(value)) { "Неверный формат email: $value" }
            field = value
        }

    var git: String? = git
        set(value) {
            require(value == null || gitValid(value)) { "Неверный формат Git URL: $value" }
            field = value
        }

    init {
        validateFullName()
        require(hasContactInfo()) { "Должен быть указан хотя бы один контакт" }
        require(!git.isNullOrEmpty()) { "Необходимо указать ссылку на Git" }
    }

    fun getInfo(): String {
        val initials = "${firstName.firstOrNull() ?: ""}.${middleName.firstOrNull() ?: ""}."
        val contactInfo = listOfNotNull(
            "Телефон: $phone",
            "Телеграм: $telegram",
            "Почта: $email"
        ).firstOrNull() ?: "Контактов нет"
        return "Фамилия: $lastName $initials, Git: ${git ?: "не указан"}, $contactInfo"
    }

    private fun validateFullName() {
        require(fullNameValid("$lastName $firstName $middleName")) { "Неверный формат ФИО" }
    }

    private fun hasContactInfo() = !phone.isNullOrEmpty() || !telegram.isNullOrEmpty() || !email.isNullOrEmpty()

    companion object Validators {
        fun fullNameValid(fullName: String?) = fullName?.matches(Regex("^[А-ЯЁA-Z][а-яёa-z]+(?: [А-ЯЁA-Z][а-яёa-z]+){0,2}\$")) == true
        fun phoneValid(phone: String?) = phone?.matches(Regex("^\\+?[0-9]{11}\$")) == true
        fun telegramValid(telegram: String?) = telegram?.matches(Regex("^@[A-Za-z0-9_]{5,32}\$")) == true
        fun emailValid(email: String?) = email?.matches(Regex("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")) == true
        fun gitValid(git: String?) = git?.matches(Regex("""^https:\/\/(www\.)?(github\.com|gitlab\.com|bitbucket\.org)\/[a-zA-Z0-9\-_]+$""")) == true
    }

    override fun toString() = """
        id: ${id ?: "Не задан"}
        ФИО: $lastName $firstName $middleName
        Телефон: ${phone ?: "Не указан"}
        Telegram: ${telegram ?: "Не указан"}
        Email: ${email ?: "Не указан"}
        Git: ${git ?: "Не указан"}
    """.trimIndent()
}
