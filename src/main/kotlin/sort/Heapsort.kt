package sort

object Heapsort {
    fun sort(xs: IntArray) {
        // Build a binary max heap. i.e. Create a binary max heap except the deepest nodes.
        for (i in xs.size / 2 downTo 0) heapify(xs, xs.size, i)

        for (lastIndex in xs.lastIndex downTo 0) {
            // Move the largest node, which is at the first element of the array, to the end of the array.
            xs.swap(0, lastIndex)

            // Create a binary max heap using all the nodes before the last index.
            heapify(xs, lastIndex, 0)
        }
    }

    /**
     * Create a binary max heap.
     */
    private fun heapify(xs: IntArray, nodeCount: Int, currentNodeIndex: Int) {
        var indexOfMax = currentNodeIndex
        val leftChildIndex = 2 * currentNodeIndex + 1
        val rightChildIndex = 2 * currentNodeIndex + 2
        if (leftChildIndex < nodeCount) {
            if (xs[indexOfMax] < xs[leftChildIndex]) indexOfMax = leftChildIndex
            if (rightChildIndex < nodeCount && xs[indexOfMax] < xs[rightChildIndex]) indexOfMax = rightChildIndex
            if (currentNodeIndex != indexOfMax) {
                xs.swap(currentNodeIndex, indexOfMax)
                heapify(xs, nodeCount, indexOfMax)
            }
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
