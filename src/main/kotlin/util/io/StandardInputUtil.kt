package util.io

object StandardInputUtil {
    fun readInt() = readln().toInt()
    fun readIntegers() = readln().split(' ').map(String::toInt)
    fun readZeroBasedIntegers() = readln().split(' ').map(String::toInt).map { it - 1 }

    fun safeReadIntegers() = readln().split(' ').filterNot(String::isEmpty).map(String::toInt)

    /**
     * val xs = " 1  2 ".split("\\s+".toRegex()).map(String::toInt) // throw a NumberFormatException because of leading and trailing spaces.
     * val ys = " 1  2 ".trim().split(' ').map(String::toInt) // throw a NumberFormatException because of more than one space between integers.
     * val zs = " 1  2 ".trim().split("\\s+".toRegex()).map(String::toInt) // succeeds.
     */
    @Deprecated(
            message = "Use safeReadIntegers() because it is faster.",
            replaceWith = ReplaceWith("safeReadIntegers()")
    )
    fun safeReadIntegersSlow() = readln().trim().split("\\s+".toRegex()).map(String::toInt)

    /**
     * Read a matrix when the standard input is as follows.
     * 3 <- number of rows
     * a00 a01 a02 a03
     * a10 a11 a12 a13
     * a20 a21 a22 a23
     */
    fun readMatrix() = List(readln().toInt()) {
        readln().split(' ').map(String::toInt).toIntArray()
    }

    /**
     * Read all the lines from standard input (stdin).
     */
    fun readLines() = generateSequence(::readLine)
}
