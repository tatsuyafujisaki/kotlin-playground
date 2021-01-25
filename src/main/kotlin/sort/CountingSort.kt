package sort

object CountingSort {
    fun sort(xs: IntArray) {
        val occurrences = IntArray(xs.maxOrNull()!! + 1)
        for (x in xs) {
            occurrences[x]++
        }
        var i = 0
        occurrences.forEachIndexed { j, x ->
            repeat(x) {
                xs[i++] = j
            }
        }
    }
}
