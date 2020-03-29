package sort

object MergeSort {
    fun sort(xs: IntArray): IntArray {
        if (xs.size < 2) {
            return xs
        }
        
        val middle = xs.size / 2
        return merge(sort(xs.copyOfRange(0, middle)), sort(xs.copyOfRange(middle, xs.size)))
    }

    private fun merge(lefts: IntArray, rights: IntArray): IntArray {
        var i = 0
        var j = 0
        val merged = mutableListOf<Int>()

        while (i < lefts.size && j < rights.size) {
            if (lefts[i] <= rights[j]) {
                merged.add(lefts[i])
                i++
            } else {
                merged.add(rights[j])
                j++
            }
        }

        merged.addAll((if (i < lefts.size) lefts.copyOfRange(i, lefts.size)
            else rights.copyOfRange(i, rights.size)).toTypedArray())

        return merged.toIntArray()
    }
}