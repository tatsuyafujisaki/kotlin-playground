package util

object StandardOutputUtil {
    fun IntArray.printVertically() {
        forEach { println(it) }
    }

    fun DoubleArray.printVertically() {
        forEach { println(it) }
    }

    fun Array<*>.printVertically() {
        forEach { println(it) }
    }

    fun List<*>.printVertically() {
        forEach { println(it) }
    }

    fun IntArray.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until size - 1) {
                print("${this[i]} ")
            }
            println(this[size - 1])
        }
    }

    fun LongArray.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until size - 1) {
                print("${this[i]} ")
            }
            println(this[size - 1])
        }
    }

    fun DoubleArray.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until size - 1) {
                print("${this[i]} ")
            }
            println(this[size - 1])
        }
    }

    fun Array<*>.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until size - 1) {
                print("${this[i]} ")
            }
            println(this[size - 1])
        }
    }

    fun List<*>.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until size - 1) {
                print("${this[i]} ")
            }
            println(this[size - 1])
        }
    }
}
