package util

import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileUtil {
    @Suppress("UNCHECKED_CAST")
    fun <T> readFile(file: File): T {
        ObjectInputStream(file.inputStream()).use {
            return it.readObject() as T
        }
    }

    fun <T> writeFile(file: File, xs: List<T>) {
        ObjectOutputStream(file.outputStream()).use {
            it.writeObject(xs)
        }
    }
}
