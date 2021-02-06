package util

import kotlin.system.measureTimeMillis

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
        if (isEmpty()) return
        for (i in 0 until lastIndex) print("${this[i]} ")
        println(last())
    }

    fun LongArray.printHorizontally() {
        if (isEmpty()) return
        for (i in 0 until lastIndex) print("${this[i]} ")
        println(last())
    }

    fun DoubleArray.printHorizontally() {
        if (isEmpty()) return
        for (i in 0 until lastIndex) print("${this[i]} ")
        println(last())
    }

    fun Array<*>.printHorizontally() {
        if (isEmpty()) return
        for (i in 0 until lastIndex) print("${this[i]} ")
        println(last())
    }

    fun List<*>.printHorizontally() {
        if (isEmpty()) return
        for (i in 0 until lastIndex) print("${this[i]} ")
        println(last())
    }

    fun printTime(times: Int = 1, action: () -> Unit) {
        println(
            measureTimeMillis {
                repeat(times) {
                    action()
                }
            }
        )
    }
}
