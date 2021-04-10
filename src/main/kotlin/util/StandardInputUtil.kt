package util

object StandardInputUtil {
    fun readInt() = readLine().orEmpty().toInt()
    fun readIntegers() = readLine().orEmpty().split("\\s+".toRegex()).map(String::toInt)
    fun readDoubles() = readLine().orEmpty().split("\\s+".toRegex()).map(String::toDouble)

    /**
     * val xs = " 1  2 ".split("\\s+".toRegex()).map(String::toInt) // throw a NumberFormatException because of leading and trailing spaces.
     * val ys = " 1  2 ".trim().split(' ').map(String::toInt) // throw a NumberFormatException because of more than one space between integers.
     * val zs = " 1  2 ".trim().split("\\s+".toRegex()).map(String::toInt) // succeeds.
     */
    fun safeReadIntegers1() = readLine().orEmpty().trim().split("\\s+".toRegex()).map(String::toInt)
    fun safeReadIntegers2() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toInt)

    fun safeReadDoubles1() = readLine().orEmpty().trim().split("\\s+".toRegex()).map(String::toDouble)
    fun safeReadDoubles2() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toInt)

    /**
     * Read all the lines.
     */
    fun readLines() = generateSequence(::readLine)
}
