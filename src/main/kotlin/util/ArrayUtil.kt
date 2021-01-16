package util

object ArrayUtil {
    fun getIndexOfMin(xs: IntArray): Int {
        var indexOfMin = 0
        for (i in 1 until xs.size) {
            if (xs[i] < xs[indexOfMin]) {
                indexOfMin = i
            }
        }
        return indexOfMin
    }

    fun printlnArray(xs: Array<*>) = xs.forEach { println(it) }

    fun <T> printArray(xs: Array<T>) {
        xs.forEach { print("$it ") }
    }

    fun printArray(xs: IntArray) {
        xs.forEach { print("$it ") }
    }
}
