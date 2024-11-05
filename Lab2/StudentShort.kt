class StudentShort(
    val id: Int,
    val fio: String,
    val git: String?,
    val contact: String
) {
    // Конструктор, который принимает объект Student
    constructor(student: Student) : this(
        id = student.id ?: 0,
        fio = "${student.lastName} ${student.firstName.firstOrNull() ?: ""}.${student.middleName.firstOrNull() ?: ""}.",
        git = student.git,
        contact = when {
            !student.phone.isNullOrBlank() -> "Телефон: ${student.phone}"
            !student.telegram.isNullOrBlank() -> "Телеграм: ${student.telegram}"
            !student.email.isNullOrBlank() -> "Почта: ${student.email}"
            else -> "Контактов нет"
        }
    )

    // Конструктор, который принимает ID и строку с остальной информацией
    constructor(id: Int, data: String) : this(
        id = id,
        fio = data.split(";")[0].let {
            val parts = it.split(" ")
            if (parts.size >= 2) "${parts[0]} ${parts[1].firstOrNull() ?: ""}." else parts[0]
        },
        git = data.split(";").getOrNull(2),
        contact = data.split(";").getOrNull(3)?.let {
            when {
                it.startsWith("+") -> "Телефон: $it"
                it.startsWith("@") -> "Телеграм: $it"
                it.contains("@") -> "Почта: $it"
                else -> "Контактов нет"
            }
        } ?: "Контактов нет"
    )

    // Метод для отображения информации о студенте
    fun displayInfo() {
        println("""
            ID: $id
            ФИО: $fio
            Git: ${git ?: "не указан"}
            Контакт: $contact
        """.trimIndent())
    }
}
