package util

object CollectionUtil {
    fun <T> Collection<T>.permute(result: List<T> = emptyList()): List<List<T>> =
        if (isEmpty()) listOf(result) else flatMap { (this - it).permute(result + it) }
    fun <T> Collection<T>.permuteWithoutDuplicates() = permute().distinct()
}
