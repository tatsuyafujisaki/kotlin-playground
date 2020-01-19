package util

object UrlUtil {
    /**
     * [MatchResult.groupValues] returns a list of two elements.
     * The first element is the entire match including a trailing double quotation mark we don't want.
     * The second element is the first match, which is what we want.
     */
    private fun extractDoubleQuotedUrl(s: String) = Regex("(https?://.+?)\"").find(s)?.groupValues?.last()

    fun example() {
        val html = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
        val url = extractDoubleQuotedUrl(html) // https://example.com
        println(url)
    }
}
