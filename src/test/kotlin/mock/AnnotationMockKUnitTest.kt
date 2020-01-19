package mock

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class InjectTestService {
    lateinit var service1: TestableService
    lateinit var service2: TestableService

    fun invokeService1(): String {
        return service1.getDataFromDb("Test Param")
    }
}

class AnnotationMockKUnitTest {
    @MockK
    lateinit var service1: TestableService

    @MockK
    lateinit var service2: TestableService

    @InjectMockKs
    var objectUnderTest = InjectTestService()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun givenServiceMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        // given
        every { service1.getDataFromDb("Test Param") } returns "No"
        // when
        val result = objectUnderTest.invokeService1()
        // then
        assertEquals("No", result)
    }
}