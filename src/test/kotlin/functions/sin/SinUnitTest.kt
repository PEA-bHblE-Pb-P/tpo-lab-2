package functions.sin

import functions.system.Sin
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import util.assertByOffset
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class SinUnitTest {
    private val eps = 0.000000001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @ValueSource(doubles = [-5.0, 1.000001, 2.0, Double.NaN, NEGATIVE_INFINITY, POSITIVE_INFINITY])
    @DisplayName("sin when cos is invalid")
    fun `sin mocked invalid cos`(cosValue: Double) {
        val mockedCos = mock<(Double, Double) -> Double> {
            on { invoke(1.0, eps) } doReturn cosValue
        }

        assertByOffset(
            Sin(mockedCos).invoke(1.0, eps),
            Double.NaN,
            offset
        )

        verify(mockedCos).invoke(1.0, eps)
    }
}