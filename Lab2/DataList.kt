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
    abstract fun getNames(): Array<String>
    // Метод для получения объекта DataTable, не работает и будет реализован в наследниках
    abstract fun getData(): DataTable<T>
}
