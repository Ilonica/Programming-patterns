import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

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

    private fun validateFullName() {
        require(fullNameValid("$lastName $firstName $middleName")) { "Неверный формат ФИО" }
    }

    private fun hasContactInfo() = !phone.isNullOrEmpty() || !telegram.isNullOrEmpty() || !email.isNullOrEmpty()

    companion object {
        fun fullNameValid(fullName: String?) = fullName?.matches(Regex("^[А-ЯЁA-Z][а-яёa-z]+(?: [А-ЯЁA-Z][а-яёa-z]+){0,2}\$")) == true
        fun phoneValid(phone: String?) = phone?.matches(Regex("^\\+?[0-9]{11}\$")) == true
        fun telegramValid(telegram: String?) = telegram?.matches(Regex("^@[A-Za-z0-9_]{5,32}\$")) == true
        fun emailValid(email: String?) = email?.matches(Regex("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")) == true
        fun gitValid(git: String?) = git?.matches(Regex("""^https:\/\/(www\.)?(github\.com|gitlab\.com|bitbucket\.org)\/[a-zA-Z0-9\-_]+$""")) == true

        @Throws(IOException::class, IllegalArgumentException::class)
        fun readFromTxt(path: String): List<Student> {
            val file = File(path)
            if (!file.exists()) {
                throw FileNotFoundException("Файл по адресу $path не найден.")
            }
            val studentList = mutableListOf<Student>()
            file.forEachLine { line ->
                try {
                    // Предполагаем, что строка содержит данные в формате: lastName, firstName, middleName, phone, telegram, email, git
                    val parts = line.split(",").map { it.trim() }
                    if (parts.size < 7) {
                        throw IllegalArgumentException("Недостаточно данных в строке: \"$line\".")
                    }
                    val student = Student(
                        lastName = parts[0],
                        firstName = parts[1],
                        middleName = parts[2],
                        phone = parts[3],
                        telegram = parts[4],
                        email = parts[5],
                        git = parts[6],
                        id = (Math.random() * 10).toInt() // Генерация id
                    )
                    studentList.add(student)
                } catch (e: IllegalArgumentException) {
                    println("Пропускаем строку с ошибкой: \"$line\".")
                }
            }
            if (studentList.isEmpty()) {
                throw IllegalArgumentException("В файле нет корректных данных студентов.")
            }
            return studentList
        }

        @Throws(IOException::class)
        fun writeToTxt(path: String, students: List<Student>) {
            val file = File(path)
            try {
                file.printWriter().use { writer ->
                    students.forEach { student ->
                        writer.println(
                            "${student.lastName},${student.firstName},${student.middleName}," +
                                    "${student.phone ?: ""},${student.telegram ?: ""},${student.email ?: ""},${student.git ?: ""}"
                        )
                    }
                }
            } catch (e: IOException) {
                throw IOException("Ошибка записи в файл: ${e.message}")
            }
        }
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
