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
            _phone = value
        }
        get() = _phone

    private var _telegram: String? = null
    var telegram: String?
        set(value) {
            _telegram = value
        }
        get() = _telegram

    private var _email: String? = null
    var email: String?
        set(value) {
            _email = value
        }
        get() = _email

    private var _git: String? = null
    var git: String?
        set(value) {
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
            
    override fun toString(): String {
        return """
            id: ${id ?: "Не задан"}
            ФИО: $lastName $firstName $middleName
            Телефон: ${phone ?: "Не указан"}
            Telegram: ${telegram ?: "Не указан"}
            Email: ${email ?: "Не указан"}
            GitHub: ${git ?: "Не указан"}
        """.trimIndent()
    }
}
