package util

object StandardInputUtil {
    fun readInt() = readLine().orEmpty().toInt()
    fun readIntegers() = readLine().orEmpty().split(' ').map(String::toInt)
    fun readDoubles() = readLine().orEmpty().split(' ').map(String::toDouble)

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

    /**
     * Read a matrix when the standard input is as follows.
     * 3 <- number of rows
     * a00 a01 a02 a03
     * a10 a11 a12 a13
     * a20 a21 a22 a23
     */
    fun readMatrix() = Array(readLine().orEmpty().toInt()) {
        readLine().orEmpty().split(' ').map(String::toInt).toIntArray()
    }
}
