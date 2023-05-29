package functions.csc

import functions.system.Csc
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyDouble
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import util.assertByOffset

class CscUnitTest {
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @Test
    fun `csc mocked division by zero`() {
        val mockedSin = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturn 0.0
        }

        assertByOffset(
            Csc(mockedSin).invoke(1.0, eps),
            Double.POSITIVE_INFINITY,
            offset
        )

        verify(mockedSin).invoke(1.0, eps*0.1)
    }

    @Test
    fun `csc(x) is NaN when sin(x) is NaN`() {
        val mockedSin = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturn Double.NaN
        }

        assertByOffset(
            Csc(mockedSin).invoke(10.0, eps),
            Double.NaN,
            offset
        )

        verify(mockedSin).invoke(10.0, eps*0.1)
    }

    @Test
    fun `csc(x) is NaN when sin(x) is less than eps`() {
        val eps = 0.1
        val mockedSin = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturn 0.01
        }

        assertByOffset(
            Csc(mockedSin).invoke(10.0, eps),
            Double.NaN,
            offset
        )

        verify(mockedSin).invoke(10.0, eps*0.1)
    }

    @Test
    fun `csc(x) is NaN when sin(x) is less than eps (negative case)`() {
        val eps = 0.1
        val mockedSin = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturn -0.01
        }

        assertByOffset(
            Csc(mockedSin).invoke(10.0, eps),
            Double.NaN,
            offset
        )

        verify(mockedSin).invoke(10.0, eps*0.1)
    }
}