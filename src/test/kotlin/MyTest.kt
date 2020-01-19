import kotlin.reflect.jvm.jvmName
import kotlin.test.Test
import kotlin.test.assertEquals

class MyTest {
    @Test
    fun printTypeTest() {
        val x = 0
        val y: Any = 0

        assertEquals("kotlin.Int", x.javaClass.kotlin.qualifiedName) // Faster than x::class.qualifiedName
        assertEquals("kotlin.Int", y.javaClass.kotlin.qualifiedName) // Faster than hy::class.qualifiedName

        assertEquals("Int", x.javaClass.kotlin.simpleName) // Faster than x::class.simpleName
        assertEquals("Int", y.javaClass.kotlin.simpleName) // Faster than y::class.simpleName

        assertEquals("int", x.javaClass.kotlin.jvmName) // Faster than x::class.jvmName
        assertEquals("java.lang.Integer", y.javaClass.kotlin.jvmName) // Faster than y::class.jvmName

        assertEquals("int", x.javaClass.name)
        assertEquals("java.lang.Integer", y.javaClass.name)

        assertEquals("int", x.javaClass.simpleName)
        assertEquals("Integer", y.javaClass.simpleName)

        assertEquals("int", x.javaClass.typeName)
        assertEquals("java.lang.Integer", y.javaClass.typeName)

        assertEquals("int", x.javaClass.canonicalName)
        assertEquals("java.lang.Integer", y.javaClass.canonicalName)
    }
}
