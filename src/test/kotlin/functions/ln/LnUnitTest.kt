package functions.ln

import functions.base.Ln
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.assertByOffset
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class LnUnitTest {
    private val ln = Ln()
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @Test
    fun `ln e should be equal to 1`() {
        assertByOffset(ln(Math.E, eps), 1.0, offset)
    }

    @Test
    fun `ln 1 should be equal to 0`() {
        assertByOffset(ln(1.0, eps), 0.0, offset)
    }

    /**
     * ln(NaN) is NaN
     * ln(x) is NaN when x < 0.0
     * ln(+Inf) is +Inf
     * ln(0.0) is -Inf
     */
    @Test
    fun `ln(NaN) is NaN`() {
        assertThat(ln(Double.NaN, eps)).isNaN()
    }

    @ParameterizedTest
    @ValueSource(doubles = [NEGATIVE_INFINITY, -10.0, -5.0, -1.0, -0.00001])
    fun `ln(x) is NaN when x is less than 0`(x: Double) {
        assertThat(ln(x, eps)).isNaN()
    }

    @Test
    fun `ln(+Inf) is +Inf`() {
        assertThat(ln(POSITIVE_INFINITY, eps)).isEqualTo(POSITIVE_INFINITY)
    }

    @Test
    fun `ln(0) is -Inf`() {
        assertThat(ln(0.0, eps)).isEqualTo(NEGATIVE_INFINITY)
    }
}