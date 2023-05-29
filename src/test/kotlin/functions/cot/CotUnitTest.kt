package functions.cot

import functions.system.Cot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.kotlin.*
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.PI

class CotUnitTest {
    private val eps = 0.00001
    private val sin = mock<(Double, Double) -> Double> {
        on { invoke(anyDouble(), anyDouble()) } doReturn 1.0
    }
    private val cos = mock<(Double, Double) -> Double> {
        on { invoke(anyDouble(), anyDouble()) } doReturn 1.0
    }
    private val cot = Cot(cos, sin)

    @AfterEach
    fun noMoreInteractions() = verifyNoMoreInteractions(cos, sin)

    @Test
    fun `should return +Inf when cos(x) is NaN`() {
        whenever(cos.invoke(anyDouble(), anyDouble())) doReturn Double.NaN

        assertThat(cot(PI, eps))
            .isEqualTo(POSITIVE_INFINITY)

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return +Inf when sin(x) is less than eps`() {
        whenever(sin.invoke(anyDouble(), anyDouble())) doReturn eps*0.1

        assertThat(cot(PI, eps))
            .isEqualTo(POSITIVE_INFINITY)

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return +Inf when abs(sin(x)) is less than eps (negative case)`() {
        whenever(sin.invoke(anyDouble(), anyDouble())) doReturn -eps*0.1

        assertThat(cot(PI, eps))
            .isEqualTo(POSITIVE_INFINITY)

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return +Inf when sin(x) is NaN`() {
        whenever(sin.invoke(anyDouble(), anyDouble())) doReturn Double.NaN

        assertThat(cot(PI, eps))
            .isEqualTo(POSITIVE_INFINITY)

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }
}