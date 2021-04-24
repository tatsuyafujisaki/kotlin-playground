package util.io

import java.util.StringTokenizer

/**
 * Usage:
 * fun main(args: Array<String>) {
 *     val n = FastScanner.nextInt()
 *     val pw = PrintWriter(System.out, false /* disables autoFlush to write at once */)
 *     FastScanner.close()
 *     pw.close()
 * }
 */
object FastScanner {
    private val br = System.`in`.bufferedReader()
    private var st = StringTokenizer("")

    private fun next(): String {
        while (!st.hasMoreTokens()) st = StringTokenizer(br.readLine())
        return st.nextToken()
    }

    fun nextInt() = next().toInt()
    fun nextZeroBasedInt() = next().toInt() - 1
    fun nextLong() = next().toLong()
    fun nextDouble() = next().toDouble()

    fun nextIntegers(n: Int): IntArray {
        val xs = IntArray(n)
        for (i in 0 until n) xs[i] = nextInt()
        return xs
    }

    fun nextZeroBasedIntegers(n: Int): IntArray {
        val xs = IntArray(n)
        for (i in 0 until n) xs[i] = nextZeroBasedInt()
        return xs
    }

    fun nextLongs(n: Int): LongArray {
        val xs = LongArray(n)
        for (i in 0 until n) xs[i] = nextInt().toLong()
        return xs
    }

    fun close() {
        br.close()
    }
}
