fun main() {
    val students = mutableListOf(
        Student("Орлова", "Анна", "Петровна", _id = 1, _phone = "1234567890", _email = "orlova@mail.com"),
        Student("Петров", "Владимир", "Николаевич", _id = 2, _telegram = "@petrov", _git = "github.com/petrov"),
        Student("Сидоров", "Юрий", "Алексеевич", _id = 3, _phone = "0987654321", _email = "sidorov@mail.com", _telegram = "@sidorov", _git = "github.com/sidorov")
    )

    val result = students.joinToString(separator = "\n\n")
    println(result)
}
