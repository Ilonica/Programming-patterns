class DataListStudentShort(elements: Array<StudentShort>) : DataList<StudentShort>(elements) {
    override fun getNames(): Array<String> {
        // Возвращаем названия столбцов таблицы
        return arrayOf("ID", "ФИО", "Git", "Контакт")
    }

    // Печать названий
    fun printNames(names: Array<String>) {
        println(names.joinToString(" "))
    }

    // Извлечение данных
    override fun fetchData(): Array<Array<String?>> {
        val data = mutableListOf<Array<String?>>()
        // Получаем список выбранных индексов
        val selectedIndices = getSelectedIds()
        for (index in selectedIndices) {
            val student = elements[index]
            val row = arrayOf(
                (index + 1).toString(), // Индексация с 1
                student.fio,
                student.git,
                student.contact
            )
            data.add(row)
        }
        return data.toTypedArray()
    }

    // Форматирование данных
    override fun formatData(data: Array<Array<String?>>): Array<Array<String>> {
        val formattedData = mutableListOf<Array<String>>()
        for (row in data) {
            val newRow = arrayOf(
                row[0] ?: "",
                row[1] ?: "",
                row.getOrNull(2) ?: "",
                row.getOrNull(3) ?: ""
            )
            formattedData.add(newRow)
        }
        return formattedData.toTypedArray()
    }

    override fun getData(): DataTable<String> {
        val rawData = fetchData()
        val formattedData = formatData(rawData)
        return DataTable(formattedData)
    }
}
