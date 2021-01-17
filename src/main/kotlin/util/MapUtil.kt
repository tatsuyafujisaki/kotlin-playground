package util

object MapUtil {
    fun sortByValue(map: Map<Any, Int>) = map.toList().sortedBy { it.second }.toMap()

    fun sortByValueDescending(map: Map<Any, Int>) = map.toList().sortedByDescending { it.second }.toMap()

    fun getEntryOfMaxValue(map: Map<Any, Int>) = map.maxByOrNull { it.value }
}
