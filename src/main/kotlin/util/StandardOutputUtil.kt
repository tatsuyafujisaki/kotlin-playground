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
            for (i in 0 until lastIndex) {
                print("${this[i]} ")
            }
            println(this[lastIndex])
        }
    }

    fun LongArray.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until lastIndex) {
                print("${this[i]} ")
            }
            println(this[lastIndex])
        }
    }

    fun DoubleArray.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until lastIndex) {
                print("${this[i]} ")
            }
            println(this[lastIndex])
        }
    }

    fun Array<*>.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until lastIndex) {
                print("${this[i]} ")
            }
            println(this[lastIndex])
        }
    }

    fun List<*>.printHorizontally() {
        if (isNotEmpty()) {
            for (i in 0 until lastIndex) {
                print("${this[i]} ")
            }
            println(this[lastIndex])
        }
    }
}
