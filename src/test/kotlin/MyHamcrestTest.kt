import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test

class MyHamcrestTest {
    @Test
    fun assertTrueTest() {
        val actual = true
        assertThat(actual, `is`(true))
    }

    @Test
    fun assertEqualsTest() {
        val expected = "a"
        val actual = "a"
        assertThat(actual, `is`(expected))
    }

    @Test
    fun assertNullTest() {
        val x: String? = null
        assertThat(x, `is`(nullValue()))
    }

    @Test
    fun assertIsTest() {
        val value = ""
        assertThat(value, `is`(instanceOf(String::class.java)))
    }

    @Test
    fun assertContainsTest() {
        val range = listOf("a", "b", "c")
        val value = "b"
        assertThat(range, hasItem(value))
    }
}
