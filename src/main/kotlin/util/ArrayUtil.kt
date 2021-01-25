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
            val occurrences = IntArray(maxOrNull()!! + 1)
            for (x in this) {
                occurrences[x]++
            }
            val middleIndex = size / 2
            var i = 0
            occurrences.forEachIndexed { j, x ->
                repeat(x) {
                    this[i] = j
                    if (i == middleIndex) return onMiddleIndex(this, i)
                    i++
                }
            }
            return Double.MIN_VALUE
        }
        return if (size % 2 == 0) {
            median { ys, j -> (ys[j - 1] + ys[j]) / 2.0 }
        } else {
            median { ys, j -> ys[j].toDouble() }
        }
    }
}
