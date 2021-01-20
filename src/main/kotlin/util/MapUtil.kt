package util

object MapUtil {
    fun <T> MutableMap<T, Int>.increment(key: T, value: Int = 1) = merge(key, value, Int::plus)
    fun <T> MutableMap<T, Long>.increment(key: T, value: Long = 1) = merge(key, value, Long::plus)
    fun <T> Map<T, Int>.sortByValue() = toList().sortedBy { it.second }.toMap()
    fun <T> Map<T, Int>.sortByValueDescending() = toList().sortedByDescending { it.second }.toMap()
    fun <T> Map<T, Int>.getEntryOfMaxValue() = maxByOrNull { it.value }
}
