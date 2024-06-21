package util

object MapUtil {
    fun <K, V : Comparable<V>> sortedByValue(map: Map<K, V>) = map.toList().sortedBy { it.second }.toMap()

    fun <K, V : Comparable<V>> sortedByValueDescending(map: Map<K, V>) =
        map.toList().sortedByDescending { it.second }.toMap()

    fun <K, V : Comparable<V>> getEntryOfMaxValue(map: Map<K, V>) = map.maxByOrNull { it.value }
}
