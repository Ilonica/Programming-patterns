fun main() {
    // Создаем несколько объектов StudentShort
    val students = arrayOf(
        StudentShort(1, "Иванов И.И.", "https://github.com/ivanov", "Телефон: +123456789"),
        StudentShort(2, "Петров П.П.", null, "Почта: petrov@example.com"),
        StudentShort(3, "Сидоров С.С.", "https://gitlab.com/sidorov", "Телеграм: @sidorov")
    )

    // Создаем объект DataListStudentShort
    val dataList = DataListStudentShort(students)

    // Выделяем нескольких студентов
    dataList.select(0)
    dataList.select(2)

    // Печатаем заголовки
    val columnNames = dataList.getNames()
    dataList.printNames(columnNames)

    // Получаем и печатаем данные таблицы
    val dataTable = dataList.getData()
    println(dataTable)
}
