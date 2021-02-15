package util

import java.util.Stack

object StackUtil {
    fun Stack<*>.popOrNull() = if (empty()) null else pop() // avoids an EmptyStackException.
}
