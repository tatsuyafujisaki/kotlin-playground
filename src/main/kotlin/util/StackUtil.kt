package util

import java.util.Stack

object StackUtil {
    fun Stack<*>.safePop() = if (empty()) null else pop() // avoids an EmptyStackException.
}
