abstract class DataList<T>(val elements: Array<T>) {
    private val selectedIndices = mutableSetOf<Int>()
    // Метод для выделения элемента по номеру
    fun select(number: Int) {
        if (number in elements.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Индекс вне диапазона")
        }
    }
    // Метод для получения массива ID выделенных элементов
    fun getSelectedIds() = selectedIndices.toIntArray()
    // Метод для получения массива наименований атрибутов, кроме ID, не работает и будет реализован в наследниках
    open fun getNames(): Array<String>{
        throw UnsupportedOperationException("Метод должен быть реализован во вложенном классе")
    }
    // Метод для получения объекта DataTable, не работает и будет реализован в наследниках
    open fun getData(): DataTable<String>{
        val data = fetchData()
        val newData = formatData(data)
        return DataTable(newData)
    }

    // Метод для извлечение данных
    abstract fun fetchData() : Array<Array<String?>>
    // Метод для форматирования данных
    abstract fun formatData(data:Array<Array<String?>>): Array<Array<String>>
}
