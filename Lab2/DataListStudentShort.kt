class DataListStudentShort(elements: Array<StudentShort>) : DataList<StudentShort>(elements) {
    override fun getNames(): Array<String> {
        // Возвращаем названия столбцов таблицы
        return arrayOf("ID", "ФИО", "Git", "Контакт")
    }

    override fun getData(): DataTable<StudentShort> {
        // Создаем таблицу данных с объектами типа StudentShort
        val tableData = elements.map { student ->
            arrayOf(
                student // Возвращаем весь объект StudentShort
            )
        }.toTypedArray()

        return DataTable(tableData)
    }
}
