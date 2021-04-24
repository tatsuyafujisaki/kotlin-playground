package util.io

import java.io.Closeable
import java.io.PrintWriter
import java.util.StringTokenizer

// Usage of FastScanner
fun main() {
    FastScanner().use { fs ->
        PrintWriter(System.out, false /* writes at once */).use { pw ->
            val n = fs.nextInt()
            pw.println("Hello, World!")
        }
    }
}

class FastScanner : Closeable {
    private val br = System.`in`.bufferedReader()
    private var st = StringTokenizer("")

    private fun next(): String {
        while (!st.hasMoreTokens()) st = StringTokenizer(br.readLine())
        return st.nextToken()
    }

    fun nextInt() = next().toInt()
    fun nextZeroBasedInt() = next().toInt() - 1

    fun nextIntegers(n: Int) = IntArray(n) {
        nextInt()
    }

    fun nextZeroBasedIntegers(n: Int) = IntArray(n) {
        nextZeroBasedInt()
    }

    override fun close() {
        br.close()
    }
}
