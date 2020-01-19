package datastructure

import java.util.Stack

class SafeStack<T> {
    private val stack = Stack<T>()

    fun push(item: T) = stack.push(item)
    fun popOrNull() = if (stack.empty()) null else stack.pop() // avoids an EmptyStackException.
    fun peekOrNull() = stack.lastOrNull()
}
