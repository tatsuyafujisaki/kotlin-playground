package converters

import java.util.SortedSet

class Compressor<T : Comparable<T>>(originals: SortedSet<T>) {
    val compressed = originals.indices
    private val compressMap = originals.withIndex().associate { it.value to it.index }.toMap()
    private val decompressMap = originals.withIndex().associate { it.index to it.value }.toMap()
    fun compress(original: T) = compressMap[original]!!
    fun decompress(compressed: Int) = decompressMap[compressed]!!
}
