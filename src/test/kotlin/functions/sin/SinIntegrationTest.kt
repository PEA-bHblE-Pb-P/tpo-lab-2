package functions.sin

import functions.system.Sin
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.assertByOffset

class SinIntegrationTest {
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
}