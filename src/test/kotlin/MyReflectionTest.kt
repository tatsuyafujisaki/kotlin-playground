import kotlin.reflect.jvm.jvmName
import kotlin.test.Test
import kotlin.test.assertEquals

class MyReflectionTest {
    @Test
    fun printTypeTest() {
        val x = 0
        val y: Any = 0

        assertEquals("kotlin.Int", x::class.java.kotlin.qualifiedName) // Faster than x::class.qualifiedName
        assertEquals("kotlin.Int", y::class.java.kotlin.qualifiedName) // Faster than hy::class.qualifiedName

        assertEquals("Int", x::class.java.kotlin.simpleName) // Faster than x::class.simpleName
        assertEquals("Int", y::class.java.kotlin.simpleName) // Faster than y::class.simpleName

        assertEquals("int", x::class.java.kotlin.jvmName) // Faster than x::class.jvmName
        assertEquals("java.lang.Integer", y::class.java.kotlin.jvmName) // Faster than y::class.jvmName

        assertEquals("int", x::class.java.name)
        assertEquals("java.lang.Integer", y::class.java.name)

        assertEquals("int", x::class.java.simpleName)
        assertEquals("Integer", y::class.java.simpleName)

        assertEquals("int", x::class.java.typeName)
        assertEquals("java.lang.Integer", y::class.java.typeName)

        assertEquals("int", x::class.java.canonicalName)
        assertEquals("java.lang.Integer", y::class.java.canonicalName)
    }
}
