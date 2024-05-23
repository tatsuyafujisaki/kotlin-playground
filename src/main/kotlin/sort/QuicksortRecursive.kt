package sort

object QuicksortRecursive {
    fun sort(xs: IntArray): IntArray {
        if (xs.isEmpty()) return xs
        val pivot = xs[xs.size / 2]
        return sort(xs.filter { it < pivot }.toIntArray()) +
                xs.filter { it == pivot } +
                sort(xs.filter { it > pivot }.toIntArray())
    }
}
