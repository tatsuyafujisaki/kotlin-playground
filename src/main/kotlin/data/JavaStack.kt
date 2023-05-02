package data

import java.util.Stack

private class JavaStack<T> {
    private val stack = Stack<T>()

    fun push(item: T) = stack.push(item)
    fun peek() = stack.peek() // throws EmptyStackException if the stack is empty.
    fun pop() = stack.pop()
    fun peekOrNull() = stack.lastOrNull()
    fun popOrNull() = if (stack.empty()) null else stack.pop() // avoids an EmptyStackException.
}

private fun main() {
    val stack = JavaStack<String>()
    stack.push("a")
    stack.push("b")

    println(stack.peek()) // b
    println(stack.pop()) // b
    println(stack.pop()) // a
    println(stack.peekOrNull()) // null
    println(stack.popOrNull()) // null
}
