package modules

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class CosTest {
    private val cos = BaseModuleImpl().cos
    private val eps = 0.00000000001
    private val offset = Offset.offset(0.000000001)

    @ParameterizedTest
    @CsvFileSource(resources = ["/cos.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(cos(x, eps)).isCloseTo(result, offset)
    }
}
