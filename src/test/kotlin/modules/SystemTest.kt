package modules

import functions.system.Cot
import functions.system.Log
import functions.system.Sin
import functions.system.Tan
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import util.assertByOffset

class SystemTest {
    private val function = SystemModuleImpl(
        TableBaseModule().cos,
        TableBaseModule().ln,
        Cot(TableBaseModule().cos, Sin(TableBaseModule().cos)),
        Tan(TableBaseModule().cos, Sin(TableBaseModule().cos)),
        Sin(TableBaseModule().cos),
        Log(TableBaseModule().ln),
    ).system
    private val eps = 0.001
    private val secondaryEps = 0.0000001

    @ParameterizedTest
    @CsvFileSource(resources = ["/system.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        assertByOffset(function(x, secondaryEps), result, Offset.offset(eps))
    }
}
