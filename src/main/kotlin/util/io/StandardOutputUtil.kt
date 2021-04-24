package util.io

import kotlin.system.measureTimeMillis

object StandardOutputUtil {
    fun IntArray.printVertically() {
        forEach {
            println(it)
        }
    }

    fun LongArray.printVertically() {
        forEach {
            println(it)
        }
    }

    fun DoubleArray.printVertically() {
        forEach {
            println(it)
        }
    }

    fun Array<*>.printVertically() {
        forEach {
            println(it)
        }
    }

    fun Iterable<*>.printVertically() {
        forEach {
            println(it)
        }
    }

    fun IntArray.printHorizontally() {
        println(joinToString(" "))
    }

    fun LongArray.printHorizontally() {
        println(joinToString(" "))
    }

    fun DoubleArray.printHorizontally() {
        println(joinToString(" "))
    }

    fun Array<*>.printHorizontally() {
        println(joinToString(" "))
    }

    fun Iterable<*>.printHorizontally() {
        println(joinToString(" "))
    }

    fun Array<IntArray>.printMatrix() {
        forEach {
            println(it.joinToString(" "))
        }
    }

    fun Array<LongArray>.printMatrix() {
        forEach {
            println(it.joinToString(" "))
        }
    }

    fun Array<DoubleArray>.printMatrix() {
        forEach {
            println(it.joinToString(" "))
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
