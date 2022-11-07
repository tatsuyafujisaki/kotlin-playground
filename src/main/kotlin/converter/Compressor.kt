package converter

import java.util.SortedSet

class Compressor<T : Comparable<T>>(set: SortedSet<T>) {
    private val compressed = set.mapIndexed { i, x -> x to i }.toMap()
    private val decompressed = set.mapIndexed { i, x -> i to x }.toMap()
    fun getCompressedIndex(x: T) = compressed[x]!!
    fun getItem(compressedIndex: Int) = decompressed[compressedIndex]!!
}
