package sort

object RadixSort {
    fun sort(xs: IntArray) {
        require(xs.all { 0 <= it })
        var place = 1
        repeat(xs.max()!!.toString().length) {
            countingSort(xs, place)
            place *= 10
        }
    }

    private fun countingSort(xs: IntArray, place: Int) {
        val sorted = IntArray(xs.size)
        val frequency = IntArray(10)
        for (x in xs) {
            frequency[getDigit(x, place)]++
        }
        for (i in 1 until frequency.size) {
            frequency[i] += frequency[i - 1]
        }
        /**
         * At this point,
         * frequency[0] ... frequency of '0'
         * frequency[1] ... frequency of '0' + frequency of '1'
         * .
         * .
         * .
         * frequency[9] ... frequency of '0' + frequency of '1' + ... + frequency of '9'
         */
        for (i in xs.lastIndex downTo 0) {
            val digit = getDigit(xs[i], place)
            sorted[frequency[digit] - 1] = xs[i]
            frequency[digit]--
        }
        sorted.copyInto(xs)
    }

    private fun getDigit(x: Int, place: Int) = (x / place) % 10
}