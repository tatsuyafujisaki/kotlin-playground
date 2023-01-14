import kotlin.test.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class MyMockitoTest {
    private val original = "abc"
    private val uppercase = "ABC"
    private val s = "sample"

    @Test
    fun test1() {
        val mock = mock<MockMeInterface> {
            on { uppercase(original) }.doReturn(uppercase)
        }

        val clazz = MyClass1(mock)
        clazz.setP1(s)
        clazz.f1wrapper()
        clazz.myUpperCaseWrapper(original)

        verify(mock).p1 = s
        verify(mock, never()).p2 = anyString()
        verify(mock).f1()
        verify(mock, never()).f2()
        verify(mock).uppercase(uppercase)
    }

    @Test
    fun test2() {
        val mock = mock<OpenMockMe>()

        val clazz = MyClass2(mock)
        clazz.setP1(s)
        clazz.f1wrapper()
        clazz.myUpperCaseWrapper(original)

        verify(mock).f1()
        verify(mock, never()).f2()
        verify(mock).uppercase(uppercase)
    }
}

interface MockMeInterface {
    var p1: String
    var p2: String

    fun f1() {}
    fun f2() {}
    fun uppercase(s: String): String
}

open class OpenMockMe {
    lateinit var p1: String

    open fun f1() {}
    open fun f2() {}
    open fun uppercase(s: String) {}
}

class MyClass1(private val clazz: MockMeInterface) {
    fun setP1(s: String) {
        clazz.p1 = s
    }

    fun f1wrapper() {
        clazz.f1()
    }

    fun myUpperCaseWrapper(s: String) {
        clazz.uppercase(s.uppercase())
    }
}

class MyClass2(private val clazz: OpenMockMe) {
    fun setP1(s: String) {
        clazz.p1 = s
    }

    fun f1wrapper() {
        clazz.f1()
    }

    fun myUpperCaseWrapper(s: String) {
        clazz.uppercase(s.uppercase())
    }
}
