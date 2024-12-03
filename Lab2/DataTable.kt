class DataTable<T>(private val data: Array<Array<T>>) {

    // Метод для получения элемента по номеру строки и столбца
    fun getElement(row: Int, col: Int): T {
        require(row in data.indices) { "Некорректный индекс строки: $row" }
        require(col in data[row].indices) { "Некорректный индекс столбца: $col" }
        return data[row][col]
    }

    // Метод для получения количества строк в таблице
    fun getRowCount(): Int = data.size

    // Метод для получения количества столбцов в таблице
    fun getColumnCount(): Int = if (data.isNotEmpty()) data[0].size else 0

    // Метод для вывода таблицы в виде строки
    override fun toString(): String {
        return data.joinToString(separator = "\n") { row ->
            row.joinToString(separator = "\t") { it.toString() }
        }
    }
}
