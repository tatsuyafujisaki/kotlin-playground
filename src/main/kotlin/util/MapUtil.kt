package util

object MapUtil {
    fun <T> MutableMap<T, Int>.increment(key: T, value: Int = 1) {
        merge(key, value, Int::plus)
    }

    fun <T> MutableMap<T, Long>.increment(key: T, value: Long = 1) {
        merge(key, value, Long::plus)
    }

    fun <T> MutableMap<T, Int>.decrement(key: T, value: Int = 1) {
        merge(key, value, Int::minus)
    }

    fun <T> MutableMap<T, Long>.decrement(key: T, value: Long = 1) {
        merge(key, value, Long::minus)
    }

    fun <T> MutableMap<T, Int>.decrementIfPresent(key: T, value: Int = 1) {
        merge(key, value, Int::minus)
        if (this[key]!! < 0) {
            remove(key)
        }
    }

    fun Map<*, Int>.sortByValue() = toList().sortedBy { it.second }.toMap()
    fun Map<*, Int>.sortByValueDescending() = toList().sortedByDescending { it.second }.toMap()
    fun Map<*, Int>.getEntryOfMaxValue() = maxByOrNull { it.value }
}
