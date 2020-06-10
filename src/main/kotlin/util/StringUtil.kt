package util

object StringUtil {
    fun String?.isNeitherNullNorEmpty(): Boolean = this != null && isNotEmpty()
    fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()

    /**
     * groupValues returns a list of two elements.
     * The first element is the entire match including a trailing double quotation mark we don't want.
     * The second element is the first match, which is what we want.
     */
    fun extractDoubleQuotedUrl(s: String) = Regex("(https?://.+?)\"").find(s)?.groupValues?.last()

    fun extractDoubleQuotedUrl2(s: String) =
        Regex("(https?://.+?)\"").find(s)?.destructured?.component1()

    fun sampleUsage() {
        val html = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
        val url = extractDoubleQuotedUrl(html) // https://example.com
        val url2 = extractDoubleQuotedUrl2(html) // https://example.com
        println(url)
        println(url2)
    }
}