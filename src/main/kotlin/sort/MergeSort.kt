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
        var leftIndex = 0
        var rightIndex = 0
        val merged = mutableListOf<Int>()
        while (true) {
            if (lefts[leftIndex] <= rights[rightIndex]) {
                merged.add(lefts[leftIndex++])
                if(leftIndex == lefts.size) {
                    rights.copyOfRange(rightIndex, rights.size).toCollection(merged)
                    break
                }
            } else {
                merged.add(rights[rightIndex++])
                if(rightIndex == rights.size) {
                    lefts.copyOfRange(leftIndex, lefts.size).toCollection(merged)
                    break
                }
            }
        }
        return merged.toIntArray()
    }
}