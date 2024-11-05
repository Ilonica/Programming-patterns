fun main() {
      try {
        val input = "Иванов; Иван; Иванович; +71234567890; @ivanov; ivanov@example.com; https://github.com/ivanov"
        val student = Student.fromStringToStudent(input)
        println(student)
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    }
}
