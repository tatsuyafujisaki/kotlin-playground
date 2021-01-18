package util

object StringUtil {
    fun String?.isNeitherNullNorEmpty(): Boolean = this != null && isNotEmpty()
    fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
    fun String.equalsIgnoreCase(s2: String) = equals(s2, true)
    fun String.alphabetized() = toCharArray().sorted().joinToString("")
    
    fun String.substrings() = sequence {
        for (i in indices) {
            for (j in i + 1..length) {
                yield(substring(i, j))
            }
        }
    }.toList()
}
