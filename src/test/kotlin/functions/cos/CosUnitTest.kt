package functions.cos

import functions.base.Cos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class CosUnitTest {
    private val cos = Cos()
    private val eps = 0.00001

    /**
     * cos(NaN|+Inf|-Inf) is NaN
     */
    @ParameterizedTest
    @ValueSource(doubles = [Double.NaN, POSITIVE_INFINITY, NEGATIVE_INFINITY])
    fun `cos(NaN or +Inf or -Inf) is NaN`(x: Double) {
        assertThat(cos(x, eps)).isNaN()
    }
}