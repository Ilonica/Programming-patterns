class Student(var lastName: String, var firstName: String, var middleName: String) {
    private var _id: Int? = null
    var id: Int?
        set(value) {
            _id = value
        }
        get() = _id

    private var _phone: String? = null
    var phone: String?
         set(value) {
            if (value != null && !phoneValid(value)) {
                throw IllegalArgumentException("Формат номера телефона неверный: $value")
            }
            _phone = value
        }
        get() = _phone

    private var _telegram: String? = null
    var telegram: String?
         set(value) {
            if (value != null && !telegramValid(value)) {
                throw IllegalArgumentException("Формат telegram неверный: $value")
            }
            _telegram = value
        }
        get() = _telegram

    private var _email: String? = null
    var email: String?
       set(value) {
            if (value != null && !emailValid(value)) {
                throw IllegalArgumentException("Формат e-mail неверный: $value")
            }
            _email = value
        }
        get() = _email

    private var _git: String? = null
    var git: String?
         set(value) {
            if (value != null && !gitValid(value)) {
                throw IllegalArgumentException("Формат Git URL неверный: $value")
            }
            _git = value
        }
        get() = _git

    constructor(lastName: String, firstName: String, middleName: String, _id: Int? = null, _phone: String? = null, _telegram: String? = null, _email: String? = null, _git: String? = null)
            : this(lastName, firstName, middleName) {
        id = _id
        phone = _phone
        telegram = _telegram
        email = _email
        git = _git

        validate()
    }

    constructor(hash: Map<String, Any?>): this(
        hash["firstName"] as String,
        hash["lastName"] as String,
        hash["middleName"] as String,
        hash["id"] as Int?,
        hash["telephone"] as String?,
        hash["telegram"] as String?,
        hash["mail"] as String?
    )

    companion object {
        fun fullNameValid(fullName: String?): Boolean {
            return fullName != null && fullName.matches(Regex("^[А-ЯЁA-Z][а-яёa-z]+(?: [А-ЯЁA-Z][а-яёa-z]+){0,2}\$"))
        }

        fun phoneValid(phone: String): Boolean {
            return phone != null && phone.matches(Regex("^\\+?[0-9]{11}\$"))
        }

        fun telegramValid(telegram: String?): Boolean {
            return telegram != null && telegram.matches(Regex("^@[A-Za-z0-9_]{5,32}\$"))
        }

        fun emailValid(email: String?): Boolean {
            return email != null && email.matches(Regex("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$"))
        }

        fun gitValid(git: String?): Boolean {
            return git != null && git.matches(Regex("""^https:\/\/(www\.)?(github\.com|gitlab\.com|bitbucket\.org)\/[a-zA-Z0-9\-_]+$"""))
        }
    }

    init {
        validateFullName(lastName, firstName, middleName)
    }
    
    private fun validateFullName(lastName: String, firstName: String, middleName: String) {
        if (!fullNameValid("$lastName $firstName $middleName")) {
            throw IllegalArgumentException("Формат ФИО неверный: $lastName $firstName $middleName")
        }
    }

    private fun hasContactInfo() {
        if (phone.isNullOrEmpty() && telegram.isNullOrEmpty() && email.isNullOrEmpty()) {
            throw IllegalArgumentException("Должен быть указан хотя бы один контакт: $lastName $firstName $middleName")
        }
    }

    private fun hasGit() {
        if (git.isNullOrEmpty()) {
            throw IllegalArgumentException("Необходимо указать ссылку на Git: $lastName $firstName $middleName")
        }
    }

    private fun validate() {
        hasContactInfo()
        hasGit()
    }
            
    override fun toString(): String {
        return """
            id: ${id ?: "Не задан"}
            ФИО: $lastName $firstName $middleName
            Телефон: ${phone ?: "Не указан"}
            Telegram: ${telegram ?: "Не указан"}
            Email: ${email ?: "Не указан"}
            Git: ${git ?: "Не указан"}
        """.trimIndent()
    }
}
