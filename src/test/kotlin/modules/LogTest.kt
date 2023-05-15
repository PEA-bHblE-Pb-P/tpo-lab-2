package modules

import org.assertj.core.api.Assertions
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class LogTest : StubbedBaseModuleTest() {
    private val log = SystemModuleImpl(baseModule = BaseModuleImpl()).log
    private val eps = 0.001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @CsvFileSource(resources = ["/log2.csv"], useHeadersInDisplayName = true)
    fun testLog2ByTable(x: Double, result: Double) {
        Assertions.assertThat(log(2.0, x, eps)).isCloseTo(result, offset)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log3.csv"], useHeadersInDisplayName = true)
    fun testLog3ByTable(x: Double, result: Double) {
        Assertions.assertThat(log(3.0, x, eps)).isCloseTo(result, offset)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log5.csv"], useHeadersInDisplayName = true)
    fun testLog5ByTable(x: Double, result: Double) {
        Assertions.assertThat(log(5.0, x, eps)).isCloseTo(result, offset)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log10.csv"], useHeadersInDisplayName = true)
    fun testLog10ByTable(x: Double, result: Double) {
        Assertions.assertThat(log(10.0, x, eps)).isCloseTo(result, offset)
    }
}
