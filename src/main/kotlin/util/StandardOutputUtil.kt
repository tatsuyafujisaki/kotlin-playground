package util

import kotlin.system.measureTimeMillis

object StandardOutputUtil {
    fun p(x: Any?) = println(x)

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

    fun IntRange.printHorizontally() {
        toList().printHorizontally()
    }

    fun Array<BooleanArray>.printMatrix() {
        if (isEmpty() || first().isEmpty()) return
        for (row in indices) {
            val lastColumn = first().lastIndex
            for (column in 0 until lastColumn) print("${this[row][column]} ")
            println(this[row][lastColumn])
        }
    }

    fun Array<DoubleArray>.printMatrix() {
        if (isEmpty() || first().isEmpty()) return
        for (row in indices) {
            val lastColumn = first().lastIndex
            for (column in 0 until lastColumn) print("${this[row][column]} ")
            println("%10.2f ".format(this[row][lastColumn]))
        }
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
