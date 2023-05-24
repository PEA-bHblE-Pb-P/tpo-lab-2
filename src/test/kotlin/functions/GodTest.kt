package functions

import functions.base.Cos
import functions.base.Ln
import functions.system.Sin
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.math.cos
import kotlin.math.ln

class GodTest {
    private val eps = 0.00000000001
    private val offset = Offset.offset(0.000000001)
    private val range = (-1000..1000).toList()

    @TestFactory
    fun testSinByStLib(): Collection<DynamicTest> = Sin(cos = {x, _ -> cos(x) }).let { sin ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, sin($x), eps=$offset") {
                assertThat(sin(x, eps)).isCloseTo(kotlin.math.sin(x), offset)
            }
        }
    }

    @TestFactory
    fun testCosByStLib(): Collection<DynamicTest> = Cos().let { cos ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, cos($x), eps=$offset") {
                assertThat(cos(x, eps)).isCloseTo(cos(x), offset)
            }
        }
    }

    @TestFactory
    fun testLnByStLib(): Collection<DynamicTest> = Ln().let { ln ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, ln($x), eps=$offset") {
                assertThat(ln(x, eps)).isCloseTo(ln(x), offset)
            }
        }
    }
}