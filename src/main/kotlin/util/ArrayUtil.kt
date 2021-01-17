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

    fun Array<*>.printVertically() {
        forEach { println(it) }
    }

    fun IntArray.printVertically() {
        forEach { println("$it ") }
    }

    fun <T> Array<T>.printHorizontally() {
        for (i in 0 until size - 1) {
            print(this[i].toString() + " ")
        }
        println(this.last())
    }

    fun IntArray.printHorizontally() {
        for (i in 0 until size - 1) {
            print(this[i].toString() + " ")
        }
        println(this.last())
    }
}
