package functions.log

import functions.system.Log
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.anyDouble
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class LogUnitTest {
    private val eps = 0.000000001

    @ParameterizedTest
    @ValueSource(doubles = [NEGATIVE_INFINITY, POSITIVE_INFINITY])
    fun `log_a(b) is Inf if ln(b) is Inf and ln(a) is 0`(b: Double) {
        val mockedLnA = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturnConsecutively listOf(POSITIVE_INFINITY, 0.0)
        }

        assertThat(Log(mockedLnA).invoke(3.0, b, eps))
            .isInfinite()

        verify(mockedLnA).invoke(b, eps*0.2)
        verify(mockedLnA).invoke(3.0, eps*0.2)
    }

    @Test
    fun `log_a(b) is NaN when ln(a) is 0`() {
        val mockedLnA = mock<(Double, Double) -> Double> {
            on { invoke(anyDouble(), anyDouble()) } doReturnConsecutively listOf(2.0, 0.0)
        }

        assertThat(Log(mockedLnA).invoke(3.0, 4.0, eps))
            .isNaN()

        inOrder(mockedLnA) {
            verify(mockedLnA).invoke(4.0, eps * 0.2)
            verify(mockedLnA).invoke(3.0, eps * 0.2)
        }
    }
}