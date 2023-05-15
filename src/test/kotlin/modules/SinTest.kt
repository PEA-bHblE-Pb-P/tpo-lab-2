package modules

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class SinTest {
    private val sin = SystemModuleImpl(baseModule = BaseModuleImpl()).sin
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @CsvFileSource(resources = ["/sin.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(sin(x, eps)).isCloseTo(result, offset)
    }
}
