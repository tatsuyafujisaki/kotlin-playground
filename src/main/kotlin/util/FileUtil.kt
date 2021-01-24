package util

import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileUtil {
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
