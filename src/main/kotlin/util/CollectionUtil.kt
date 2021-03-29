package util

object CollectionUtil {
    fun <T> Collection<T>.permute(left: List<T> = emptyList(), right: Collection<T> = this): List<List<T>> =
        if (right.isEmpty()) listOf(left) else right.flatMap { permute(left + it, right - it) }

    fun <T> Collection<T>.permuteWithoutDuplicates() = permute().distinct()
}
