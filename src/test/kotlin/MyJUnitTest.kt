import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MyJUnitTest {
    @Test
    fun assertTrueTest() {
        val actual = true
        assertTrue(actual)
    }

    @Test
    fun assertNullTest() {
        val actual: String? = null
        assertNull(actual)
    }

    @Test
    fun assertIsTest() {
        val actual = ""
        assertTrue(actual is String)
    }

    @Test
    fun assertEqualsTest() {
        val expected = "a"
        val actual = "a"
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun assertCollectionContainsTest() {
        val iterable = listOf("a", "b", "c")
        val element = "b"
        assertTrue(element in iterable)
    }

    @Test
    fun assertStringContainsTest() {
        val string = "abc"
        val substring = "b"
        assertTrue(substring in string)
    }
}
