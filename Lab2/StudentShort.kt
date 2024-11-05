class StudentShort(
    id: Int,
    fio: String,
    git: String? = null,
    contact: String = "Контактов нет"
) : Person(id, fio.split(" ")[0], fio.split(" ")[1], null, git) {

    // Конструктор, принимающий объект класса Student
    constructor(student: Student) : this(
        id = student.id,
        fio = student.getFullName(),
        git = student.git,
        contact = student.getContactInfo()
    )

    // Конструктор, принимающий ID и строку
    constructor(id: Int, data: String) : this(
        id = id,
        fio = data.split(";")[0],
        git = data.split(";").getOrNull(1),
        contact = data.split(";").getOrNull(2) ?: "Контактов нет"
    )

    // Переопределенный метод для вывода информации
    override fun toString(): String {
        return """
            ID: $id
            ФИО: ${getFullName()}
            Git: ${git ?: "не указан"}
            Контакт: $contact
        """.trimIndent()
    }
}
