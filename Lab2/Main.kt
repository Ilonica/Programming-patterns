fun main() {
    val filePath = "students.txt"
    try {
        val students = Student.readFromTxt(filePath)

        println("Список студентов:")
        students.forEach {
            println(it)
        }
    } catch (e: FileNotFoundException) {
        println("Ошибка: ${e.message}")
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    } catch (e: IOException) {
        println("Ошибка ввода-вывода: ${e.message}")
    }
}
