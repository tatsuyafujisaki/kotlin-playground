import arrow.core.Eval
import org.junit.Assert
import org.junit.Test
import kotlin.reflect.jvm.jvmName

class MyTest {
    @Test
    fun printTypeTest() {
        val x = 0
        val y: Any = 0

        Assert.assertEquals("kotlin.Int", x.javaClass.kotlin.qualifiedName) // Faster than x::class.qualifiedName
        Assert.assertEquals("kotlin.Int", y.javaClass.kotlin.qualifiedName) // Faster than hy::class.qualifiedName

        Assert.assertEquals("Int", x.javaClass.kotlin.simpleName) // Faster than x::class.simpleName
        Assert.assertEquals("Int", y.javaClass.kotlin.simpleName) // Faster than y::class.simpleName

        Assert.assertEquals("int", x.javaClass.kotlin.jvmName) // Faster than x::class.jvmName
        Assert.assertEquals("java.lang.Integer", y.javaClass.kotlin.jvmName) // Faster than y::class.jvmName

        Assert.assertEquals("int", x.javaClass.name)
        Assert.assertEquals("java.lang.Integer", y.javaClass.name)

        Assert.assertEquals("int", x.javaClass.simpleName)
        Assert.assertEquals("Integer", y.javaClass.simpleName)

        Assert.assertEquals("int", x.javaClass.typeName)
        Assert.assertEquals("java.lang.Integer", y.javaClass.typeName)

        Assert.assertEquals("int", x.javaClass.canonicalName)
        Assert.assertEquals("java.lang.Integer", y.javaClass.canonicalName)
    }

    @Test
    fun test() {
        var counter: Int = 0
        val later = Eval.later { counter++; counter }
        Assert.assertEquals(0, counter)

        val firstValue = later.value()
        Assert.assertEquals(1, firstValue)
        Assert.assertEquals(1, counter)

        val secondValue = later.value()
        Assert.assertEquals(1, secondValue)
        Assert.assertEquals(1, counter)

        val thirdValue = later.value()
        Assert.assertEquals(1, thirdValue)
        Assert.assertEquals(1, counter)
    }
}