package util

object StringUtil {
    /**
     * groupValues returns a list of two elements.
     * Its first element is the entire match including a trailing double quotation mark, which is not what we want.
     * Its second element is the first match.
     *
     * Usage:
     * val html = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
     * val url = extractDoubleQuotedUrl(html) // https://example.com
     */
    fun extractDoubleQuotedUrl(s: String) = Regex("(https?://.*?)\"").find(s)?.groupValues?.last()

    /**
     * Usage:
     * val html = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
     * val url = extractDoubleQuotedUrl(html) // https://example.com
     */
    fun extractDoubleQuotedUrl2(s: String) =
        Regex("(https?://.*?)\"").find(s)?.destructured?.component1()
}
