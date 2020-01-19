package sort

object RadixSort {
    fun sort(xs: IntArray) {
        require(xs.all { 0 <= it })
        var place = 1
        repeat(xs.maxOrNull()!!.toString().length) {
            countingSort(xs, place)
            place *= 10
        }
    }

    private fun countingSort(xs: IntArray, place: Int) {
        val sorted = IntArray(xs.size)
        val appearances = IntArray(10)
        for (x in xs) appearances[getDigit(x, place)]++
        for (i in 1 until appearances.size) appearances[i] += appearances[i - 1]
        /**
         * At this point,
         * appearances[0] ... appearances of '0'
         * appearances[1] ... appearances of '0' + appearances of '1'
         * ⋮
         * appearances[9] ... appearances of '0' + appearances of '1' + ... + appearances of '9'
         */
        for (i in xs.lastIndex downTo 0) {
            val digit = getDigit(xs[i], place)
            sorted[appearances[digit] - 1] = xs[i]
            appearances[digit]--
        }
        sorted.copyInto(xs)
    }

    private fun getDigit(x: Int, place: Int) = (x / place) % 10
}
