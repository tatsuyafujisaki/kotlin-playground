package sort

object CountingSort {
    fun sort(xs: IntArray) {
        val counts = IntArray(xs.maxOrNull()!! + 1)
        for (x in xs) {
            counts[x]++
        }
        var i = 0
        counts.forEachIndexed { j, x ->
            repeat(x) {
                xs[i++] = j
            }
        }
    }
}
