package util

object StandardInputUtil {
    /** redundant wrapper */
    fun readAndDiscard() {
        readLine() // Read and discard
    }
    fun readInt() = readLine().orEmpty().trim().toInt()
    fun readIntegers() = readLine().orEmpty().split("\\s+".toRegex()).map(String::toInt)
    fun readDoubles() = readLine().orEmpty().split("\\s+".toRegex()).map(String::toDouble)

    /**
     * Read all the lines.
     */
    fun readLines() = generateSequence(::readLine)
}
