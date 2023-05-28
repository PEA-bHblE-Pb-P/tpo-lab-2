package functions

import functions.base.Cos
import functions.system.Sin
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import util.assertByOffset

class SinInternalsTest {
    private val sin  = Sin()
    private val eps = 0.000000001
    private val offset = Offset.offset(eps)

    @TestFactory
    fun `sin pi times(n) should be equal to 0`(): Collection<DynamicTest> {
        val range = (-5..5).toList()

        return range.map { i ->
            DynamicTest.dynamicTest("sin(PI*$i) is 0") {
                assertByOffset(sin(Math.PI * i, eps), 0.0, offset)
            }
        }
    }

    @Test
    fun `sin mocked invalid cos`() {
        val mockedCos = mock<Cos> {
            on { invoke(1.0, eps) } doReturn -5.0
        }
        println(mockedCos(1.0, eps))
        assertByOffset(
            Sin(mockedCos).invoke(1.0, eps),
            Double.NaN,
            offset
        )
    }
}