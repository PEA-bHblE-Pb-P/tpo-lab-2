package functions.cot

import functions.system.Cot
import functions.system.Sin
import modules.TableBaseModule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class CotTableTest {
    private val cot = Cot(TableBaseModule().cos, Sin(TableBaseModule().cos))
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @CsvFileSource(resources = ["/cot.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(cot(x, eps)).isCloseTo(result, offset)
    }
}
