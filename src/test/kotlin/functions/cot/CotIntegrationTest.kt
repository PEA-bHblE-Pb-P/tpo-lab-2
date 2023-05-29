package functions.cot

import functions.base.Cos
import functions.system.Cot
import functions.system.Sin
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CotIntegrationTest {
    private val cot  = Cot(cos = Cos(), sin = Sin())
    private val eps = 0.000000001

    @ParameterizedTest
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `cot(NaN or +Inf or -Inf) is +Inf`(x: Double) {
        assertThat(cot(x, eps)).isEqualTo(Double.POSITIVE_INFINITY)
    }
}