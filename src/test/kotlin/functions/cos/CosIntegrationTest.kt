package functions.cos

import functions.base.Cos
import functions.system.Sin
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CosIntegrationTest {
    private val sin = Sin(Cos())
    private val eps = 0.00001

    /**
     * sin(NaN|+Inf|-Inf) is NaN
     */
    @ParameterizedTest
    @ValueSource(doubles = [Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY])
    fun `sin(NaN or +Inf or -Inf) is NaN`(x: Double) {
        assertThat(sin(x, eps)).isNaN()
    }
}