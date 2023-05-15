package modules

import functions.system.Cot
import functions.system.Log
import functions.system.Sin
import functions.system.Tan
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class SystemTest {
    private val function = SystemModuleImpl(
        TableBaseModule().cos,
        Cot(TableBaseModule().cos, Sin(TableBaseModule().cos)),
        Tan(TableBaseModule().cos, Sin(TableBaseModule().cos)),
        Sin(TableBaseModule().cos),
        Log(TableBaseModule().ln),
        TableBaseModule().ln
    ).function
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @ParameterizedTest
    @CsvFileSource(resources = ["/system.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertThat(function(x, eps)).isCloseTo(result, offset)
    }
}
