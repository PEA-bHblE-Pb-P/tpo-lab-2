package functions.log

import functions.system.Log
import modules.TableBaseModule
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class LogTableTest {
    private val log = Log(TableBaseModule("").ln)
    private val eps = 0.001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @CsvFileSource(resources = ["/log3.csv"], useHeadersInDisplayName = true)
    fun testLog3ByTable(x: Double, result: Double) {
        assertThat(log(3.0, x, eps)).isCloseTo(result, offset)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log10.csv"], useHeadersInDisplayName = true)
    fun testLog10ByTable(x: Double, result: Double) {
        assertThat(log(10.0, x, eps)).isCloseTo(result, offset)
    }
}
