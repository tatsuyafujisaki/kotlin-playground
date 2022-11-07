package converter

import java.util.SortedSet

class Compressor<T : Comparable<T>>(originals: SortedSet<T>) {
    val compressed = originals.indices
    private val compressMap = originals.mapIndexed { i, x -> x to i }.toMap()
    private val decompressMap = originals.mapIndexed { i, x -> i to x }.toMap()
    fun compress(original: T) = compressMap[original]!!
    fun decompress(compressed: Int) = decompressMap[compressed]!!
}
