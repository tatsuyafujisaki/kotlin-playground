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

    fun <T> File.write(xs: List<T>) {
        ObjectOutputStream(outputStream()).use {
            it.writeObject(xs)
        }
    }
}
