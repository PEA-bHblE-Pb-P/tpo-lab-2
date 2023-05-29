package functions.tan

import functions.system.Tan
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.kotlin.*
import kotlin.math.PI

class TanUnitTest {
    private val eps = 0.00001
    private val sin = mock<(Double, Double) -> Double> {
        on { invoke(anyDouble(), anyDouble()) } doReturn 1.0
    }
    private val cos = mock<(Double, Double) -> Double> {
        on { invoke(anyDouble(), anyDouble()) } doReturn 1.0
    }
    private val tan = Tan(cos, sin)

    @AfterEach
    fun noMoreInteractions() = verifyNoMoreInteractions(cos, sin)

    @Test
    fun `should return NaN when cos(x) is NaN`() {
        whenever(cos.invoke(anyDouble(), anyDouble())) doReturn Double.NaN

        assertThat(tan(PI, eps))
            .isNaN()

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return NaN when cos(x) is less than eps`() {
        whenever(cos.invoke(anyDouble(), anyDouble())) doReturn eps*0.1

        assertThat(tan(PI, eps))
            .isNaN()

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return NaN when abs(cos(x)) is less than eps (negative case)`() {
        whenever(cos.invoke(anyDouble(), anyDouble())) doReturn -eps*0.1

        assertThat(tan(PI, eps))
            .isNaN()

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }

    @Test
    fun `should return NaN when sin(x) is NaN`() {
        whenever(sin.invoke(anyDouble(), anyDouble())) doReturn Double.NaN

        assertThat(tan(PI, eps))
            .isNaN()

        verify(cos).invoke(PI, eps)
        verify(sin).invoke(PI, eps)
    }
}