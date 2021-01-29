package util

object StringUtil {
    fun String?.isNeitherNullNorEmpty(): Boolean = this != null && isNotEmpty()
    fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
    fun String.splitLast() = with(chunked(lastIndex)) { first() to last().last() }
    fun String.equalsIgnoreCase(s2: String) = equals(s2, true)
    fun String.alphabetized() = toCharArray().sorted().joinToString("")
    fun String.substrings() = sequence {
        for (i in indices) {
            for (j in i + 1..length) {
                yield(substring(i, j))
            }
        }
    }
    fun String.isAllSameChars(): Boolean {
        if (length == 0) return true
        val firstChar = first()
        for (i in 1 until length) if (this[i] != firstChar) return false
        return true
    }
}
