package util.io

import java.io.Closeable
import java.util.StringTokenizer

/** Usage
FastScanner().use { fs ->
    PrintWriter(System.out, false /* writes at once */).use {  pw ->
        val n = fs.nextInt()
        pw.println()
    }
}
 */
class FastScanner : Closeable {
    private val br = System.`in`.bufferedReader()
    private var st = StringTokenizer("")

    private fun next(): String {
        while (!st.hasMoreTokens()) st = StringTokenizer(br.readLine())
        return st.nextToken()
    }

    fun nextInt() = next().toInt()
    fun nextZeroBasedInt() = next().toInt() - 1
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

    override fun close() {
        br.close()
    }
}
