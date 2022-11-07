package util

import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.PrintWriter

object FileUtil {
    /** @param pathname a sibling of the src folder */
    fun readText(pathname: String) = File(pathname).readText()

    /** @param pathname a sibling of the src folder */
    fun readLines(pathname: String) = File(pathname).readLines()

    fun <T> readAndPrint(pathname: String, block: (String) -> T) {
        println(block(File(pathname).readText()))
    }

    /** @param pathname a sibling of the src folder */
    fun writeLines(pathname: String, block: (PrintWriter) -> Unit) = File(pathname).printWriter().use {
        block(it)
    }

    /** @param pathname a sibling of the src folder */
    fun writeLines(pathname: String, times: Int, x: String) {
        writeLines(pathname) { pw ->
            repeat(times) {
                pw.println(x)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> File.read(): T {
        ObjectInputStream(inputStream()).use {
            return it.readObject() as T
        }
    }

    fun File.write(xs: List<*>) {
        ObjectOutputStream(outputStream()).use {
            it.writeObject(xs)
        }
    }
}
