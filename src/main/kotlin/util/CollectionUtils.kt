package util

object CollectionUtils {
    fun <T> addOrRemove(xs: Collection<T>?, x: T) = xs?.run {
        if (any { it == x }) minus(x) else plus(x)
    }
}