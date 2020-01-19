package mock

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class Foo {
    lateinit var name: String
    lateinit var bar: Bar
}

class Bar {
    lateinit var nickname: String
}

class HierarchicalMockKUnitTest {
    @Test
    fun givenHierarchicalClass_whenMockingIt_thenReturnProperValue() {
        val foo = mockk<Foo> {
            every { name } returns "Karol"
            every { bar } returns mockk {
                every { nickname } returns "Tomato"
            }
        }

        val result = foo.bar.nickname

        assertEquals("Tomato", result)
    }
}