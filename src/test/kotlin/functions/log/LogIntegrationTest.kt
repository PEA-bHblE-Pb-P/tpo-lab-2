package functions.log

import functions.system.Log
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LogIntegrationTest {
    private val log = Log()
    private val eps = 0.000000001

    @ParameterizedTest
    @ValueSource(doubles = [Double.NEGATIVE_INFINITY, -4.0, -1.0, 0.0, 1.0])
    fun `log_a(b) is NaN for a equal to or less than 0 or equal to 1`(a: Double) {
        Assertions.assertThat(log(a, 5.0, eps))
            .isNaN()
    }
}