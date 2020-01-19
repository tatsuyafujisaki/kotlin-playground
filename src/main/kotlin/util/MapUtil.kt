package util

object MapUtil {
    fun <T> increment(map: MutableMap<T, Int>, key: T, value: Int = 1) {
        if (map.containsKey(key)) {
            map.merge(key, value, Int::plus)
        }
    }

    fun <T> decrement(map: MutableMap<T, Int>, key: T, value: Int = 1) {
        if (map.containsKey(key)) {
            map.merge(key, value, Int::minus)
        }
    }

    fun <K, V : Comparable<V>> sortedByValue(map: Map<K, V>) = map.toList().sortedBy { it.second }.toMap()

    fun <K, V : Comparable<V>> sortedByValueDescending(map: Map<K, V>) =
        map.toList().sortedByDescending { it.second }.toMap()

    fun <K, V : Comparable<V>> getEntryOfMaxValue(map: Map<K, V>) = map.maxByOrNull { it.value }
}
