package functions.ln

import modules.BaseModuleImpl
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class LnTableTest {
    private val ln = BaseModuleImpl().ln
    private val eps = 0.00000001
    private val offset = Offset.offset(0.000001)

    @ParameterizedTest
    @CsvFileSource(resources = ["/ln.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(ln(x, eps)).isCloseTo(result, offset)
    }
}
