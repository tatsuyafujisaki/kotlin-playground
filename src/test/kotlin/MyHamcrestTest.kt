import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
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
        val actual: String? = null
        assertThat(actual, `is`(nullValue()))
    }

    @Test
    fun assertIsTest() {
        val actual = ""
        assertThat(actual, `is`(instanceOf(String::class.java)))
    }

    @Test
    fun assertContainsTestForCollection() {
        val actual = listOf("a", "b", "c")
        val item = "b"
        assertThat(actual, hasItem(item))
    }

    @Test
    fun assertContainsTestForString() {
        val actual = "abc"
        val substring = "b"
        assertThat(actual, containsString(substring))
    }
}
