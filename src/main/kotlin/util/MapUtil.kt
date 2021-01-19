package util

object MapUtil {
    fun <T> MutableMap<T, Int>.increment(key: T) = merge(key, 1, Int::plus)
    fun <T> Map<T, Int>.sortByValue() = toList().sortedBy { it.second }.toMap()
    fun <T> Map<T, Int>.sortByValueDescending() = toList().sortedByDescending { it.second }.toMap()
    fun <T> Map<T, Int>.getEntryOfMaxValue() = maxByOrNull { it.value }
}
