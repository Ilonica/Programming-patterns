fun main() {
    val students = listOf(
        Student("Иванов", "Иван", "Иванович", phone = "+79876543210", email = "ivanov@example.com", git = "https://github.com/ivanov"),
        Student("Петров", "Петр", "Петрович", telegram = "@petrov", git = "https://github.com/petrov"),
        Student("Сидоров", "Сидор", "Сидорович", phone = "+79991234567", email = "sidorov@example.com", git = "https://gitlab.com/sidorov")
    )

    val filePath = "students_output.txt"
    try {
        Student.writeToTxt(filePath, students)
        println("Данные успешно записаны в файл $filePath")
    } catch (e: IOException) {
        println("Ошибка: ${e.message}")
    }
}
