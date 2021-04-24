package util

import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileUtil {
    /** @param pathname a sibling of the src folder */
    fun readLines(pathname: String) = File(pathname).readLines()

    /** @param pathname a sibling of the src folder */
    fun writeLines(pathname: String, block: () -> Unit) = File(pathname).printWriter().use {
        block()
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
