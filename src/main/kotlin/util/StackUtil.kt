package util

import java.util.Stack

object StackUtil {
    fun Stack<*>.popOrNull() = if (empty()) null else pop() // avoids an EmptyStackException.

    /** Redundant wrapper */
    fun Stack<*>.peekOrNull() = lastOrNull()

    fun <T> Stack<T>.peekOrElse(item: T) = lastOrNull() ?: item
}
