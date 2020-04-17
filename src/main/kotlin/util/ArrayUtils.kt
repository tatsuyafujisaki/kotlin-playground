package util

fun getIndexOfMin(xs: IntArray): Int {
    var indexOfMin = 0
    for (i in 1 until xs.size) {
        if (xs[i] < xs[indexOfMin]) {
            indexOfMin = i
        }
    }
    return indexOfMin
}

fun printArray(xs: Array<*>) = xs.forEach { println(it) }

fun printArray(xs: IntArray) {
    xs.forEach { print("$it ") }
    println()
}