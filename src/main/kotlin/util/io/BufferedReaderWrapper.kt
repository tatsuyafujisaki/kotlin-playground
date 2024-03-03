package util.io

import java.io.Closeable
import java.io.PrintWriter

// Usage of BufferedReaderWrapper
fun main() {
    BufferedReaderWrapper().use { br ->
        PrintWriter(System.out, false /* writes at once */).use { pw ->
            pw.println(br.readInt())
        }
    }
}

class BufferedReaderWrapper : Closeable {
    private val br = System.`in`.bufferedReader()

    override fun close() {
        br.close()
    }

    fun readInt() = br.readLine().toInt()
    fun readIntegers() = br.readLine().split(' ').map(String::toInt)
    fun readZeroBasedIntegers() = br.readLine().split(' ').map(String::toInt).map { it - 1 }

    /**
     * val xs = " 1  2 ".split("\\s+".toRegex()).map(String::toInt) // throw a NumberFormatException because of leading and trailing spaces.
     * val ys = " 1  2 ".trim().split(' ').map(String::toInt) // throw a NumberFormatException because of more than one space between integers.
     * val zs = " 1  2 ".trim().split("\\s+".toRegex()).map(String::toInt) // succeeds.
     */
    fun safeReadIntegers1() = br.readLine().trim().split("\\s+".toRegex()).map(String::toInt)
    fun safeReadIntegers2() = br.readLine().split(' ').filterNot(String::isEmpty).map(String::toInt)

    /**
     * Read a matrix when the standard input is as follows.
     * 3 <- number of rows
     * a00 a01 a02 a03
     * a10 a11 a12 a13
     * a20 a21 a22 a23
     */
    fun readMatrix() = List(br.readLine().toInt()) {
        br.readLine().split(' ').map(String::toInt).toIntArray()
    }
}
