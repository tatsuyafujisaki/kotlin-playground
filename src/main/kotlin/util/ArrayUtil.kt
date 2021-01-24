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
}
