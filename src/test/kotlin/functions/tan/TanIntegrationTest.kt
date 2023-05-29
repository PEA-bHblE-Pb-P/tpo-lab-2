package functions.tan

import functions.base.Cos
import functions.system.Sin
import functions.system.Tan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TanIntegrationTest {
    private val tan  = Tan(cos = Cos(), sin = Sin())
    private val eps = 0.000000001

    @ParameterizedTest
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `tan(NaN or +Inf or -Inf) is NaN`(x: Double) {
        assertThat(tan(x, eps)).isNaN()
    }
}