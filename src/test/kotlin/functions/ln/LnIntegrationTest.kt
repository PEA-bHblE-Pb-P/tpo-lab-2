package functions.ln

import functions.base.Ln
import functions.system.Log
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class LnIntegrationTest {
    private val log = Log(Ln())
    private val eps = 0.00001

    /**
     * log(x, b) is NaN if either x or b are NaN
     * log(x, b) is NaN when x < 0 or b <= 0 or b == 1.0
     * log(+Inf, +Inf) is NaN
     * log(+Inf, b) is +Inf for b > 1 and -Inf for b < 1
     * log(0.0, b) is -Inf for b > 1 and +Inf for b > 1
     */
    @ParameterizedTest
    @CsvSource("NaN,10.0", "10.0,NaN")
    fun `log(x, b) is NaN if either x or b are NaN`(a: Double, b: Double) {
        assertThat(log(a, b, eps)).isNaN()
    }

    @ParameterizedTest
    @ValueSource(doubles = [NEGATIVE_INFINITY, -10.0, -5.0, -1.0, -0.00001])
    fun `log(x, b) is NaN when x is less than 0`(x: Double) {
        assertThat(log(10.0, x, eps)).isNaN()
    }

    @ParameterizedTest
    @ValueSource(doubles = [NEGATIVE_INFINITY, -10.0, -5.0, -1.0, -0.00001, 1.0])
    fun `log(x, b) is NaN when b is less than or equal to 0 or b is equal to 1`(b: Double) {
        assertThat(log(b, 10.0, eps)).isNaN()
    }

    @Test
    fun `log(+Inf, +Inf) is NaN`() {
        assertThat(log(POSITIVE_INFINITY, POSITIVE_INFINITY, eps)).isNaN()
    }

    @ParameterizedTest
    @ValueSource(doubles = [1.000001, 5.0, 10.0, 1000000.0])
    fun `log(+Inf, b) is +Inf for b greater than 1`(b: Double) {
        assertThat(log(b, POSITIVE_INFINITY, eps)).isEqualTo(POSITIVE_INFINITY)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.000001, 0.2, 0.9])
    fun `log(+Inf, b) is -Inf for b less than 1`(b: Double) {
        assertThat(log(b, POSITIVE_INFINITY, eps)).isEqualTo(NEGATIVE_INFINITY)
    }

    @ParameterizedTest
    @ValueSource(doubles = [1.000001, 5.0, 10.0, 1000000.0])
    fun `log(0, b) is -Inf for b greater than 1`(b: Double) {
        assertThat(log(b, 0.0, eps)).isEqualTo(NEGATIVE_INFINITY)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.000001, 0.2, 0.9])
    fun `log(0, b) is +Inf for b less than 1`(b: Double) {
        assertThat(log(b, 0.0, eps)).isEqualTo(POSITIVE_INFINITY)
    }
}