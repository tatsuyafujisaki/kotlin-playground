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

    fun IntArray.medianByCountingSort(): Double {
        fun median(onMiddleIndex: (IntArray, Int) -> Double): Double {
            val counts = IntArray(maxOrNull()!! + 1)
            for (x in this) {
                counts[x]++
            }
            val indexOfMedian = size / 2
            var i = 0
            counts.forEachIndexed { j, x ->
                repeat(x) {
                    this[i] = j
                    if (i == indexOfMedian) return onMiddleIndex(this, i)
                    i++
                }
            }
            error("Unexpected to reach here.")
        }
        return if (size % 2 == 0) {
            median { xs, i -> (xs[i - 1] + xs[i]) / 2.0 }
        } else {
            median { xs, j -> xs[j].toDouble() }
        }
    }
}
