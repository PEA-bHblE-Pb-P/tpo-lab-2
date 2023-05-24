package modules

import functions.system.Sin
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class SinTest {
    private val sin = Sin(TableBaseModule().cos)
    private val eps = 0.00000000001
    private val offset = Offset.offset(0.000000001)

    @ParameterizedTest
    @CsvFileSource(resources = ["/sin.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(sin(x, eps)).isCloseTo(result, offset)
    }
}
