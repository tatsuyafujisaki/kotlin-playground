package util

object ArrayUtil {
    fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun IntArray.getIndexOfMin(): Int {
        var indexOfMin = 0
        for (i in 1 until size) {
            if (this[i] < this[indexOfMin]) {
                indexOfMin = i
            }
        }
        return indexOfMin
    }

    fun Array<*>.printVertically() {
        forEach { println(it) }
    }

    fun IntArray.printVertically() {
        forEach { println(it) }
    }

    fun <T> Array<T>.printHorizontally() {
        dropLast(1).forEach {
            print("$it ")
        }
        println(last())
    }

    fun IntArray.printHorizontally() {
        dropLast(1).forEach {
            print("$it ")
        }
        println(last())
    }
}
