package functions

import functions.system.Csc
import functions.system.Sin
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.Mockito.anyDouble
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import util.Round.roundStLibValue
import util.assertByOffset
import kotlin.math.sin

class CscInternalsTest {
    private val csc = Csc(sin = { x, _ -> sin(x).roundStLibValue() })
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @Test
    fun `csc 0 should be equal to Infinity`() {
        assertByOffset(csc(0.0, eps), Double.POSITIVE_INFINITY, offset)
    }

    @TestFactory
    fun `csc pi times(n) should be equal to Infinity`(): Collection<DynamicTest> {
        val range = (1..5).toList()

        return range.map { i ->
            DynamicTest.dynamicTest("csc(-PI*$i), csc(PI*$i) when sin(PI*$i) is ${sin(Math.PI * i)}") {
                assertByOffset(csc(Math.PI * i, eps), Double.POSITIVE_INFINITY, offset)
                assertByOffset(csc(-Math.PI * i, eps), Double.NEGATIVE_INFINITY, offset)
            }
        }
    }

    @Test
    fun `csc mocked division by zero`() {
        val mockedSin = mock<Sin> {
            on { invoke(anyDouble(), anyDouble()) } doReturn 0.0
        }
        println(mockedSin.invoke(1.0, 0.0))
        assertByOffset(
            Csc(mockedSin).invoke(1.0, eps),
            Double.NaN,
            offset
        )
    }
}