package util

object StandardInputUtil {
    fun readIntegers() = readLine().orEmpty().split(' ').map(String::toInt)
    fun readDoubles() = readLine().orEmpty().split(' ').map(String::toDouble)

    /** Handle multiple spaces */
    fun safeReadIntegers() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toInt)

    /** Handle multiple spaces */
    fun safeReadDoubles() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toDouble)

    /**
     * Read all the lines.
     */
    fun readLines() = generateSequence(::readLine)
}
