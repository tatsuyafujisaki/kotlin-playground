package data

private class ArrayDequeStack<T> {
    private val arrayDeque = ArrayDeque<T>()

    fun push(element: T) {
        arrayDeque.addFirst(element)
    }

    fun pop() = arrayDeque.removeFirst()
    fun popOrNull() = arrayDeque.removeFirstOrNull()
}

private fun main() {
    val stack = ArrayDequeStack<String>()
    stack.push("a")
    stack.push("b")

    println(stack.pop()) // b
    println(stack.pop()) // a
    println(stack.popOrNull()) // null
}
