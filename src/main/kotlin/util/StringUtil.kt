package util

object StringUtil {
    fun String?.isNeitherNullNorEmpty(): Boolean = this != null && isNotEmpty()
    fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
    fun String.equalsIgnoreCase(s2: String) = equals(s2, true)
}
