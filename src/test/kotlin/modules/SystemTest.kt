package modules

import functions.base.Cos
import functions.base.Ln
import functions.system.*
import org.assertj.core.data.Offset
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import util.CsvUtils
import util.assertByOffset

class SystemTest {

    @ParameterizedTest
    @CsvFileSource(resources = ["/system/system.csv"], useHeadersInDisplayName = true)
    fun testByTable(x: Double, result: Double) {
        val function = SystemModuleImpl(
            TableBaseModule("/system").cos,
            TableBaseModule("/system").ln,
            TableBaseModule("/system").cot,
            TableBaseModule("/system").tan,
            TableBaseModule("/system").sin,
            TableBaseModule("/system").log,
            TableBaseModule("/system").csc,
        ).system
        val eps = 0.001
        val secondaryEps = 0.0000001

        assertByOffset(function(x, secondaryEps), result, Offset.offset(eps))
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/system.csv"], useHeadersInDisplayName = true)
    fun testIntegration(x: Double, result: Double) {
        val function = SystemModuleImpl().system
        val eps = 0.001
        val secondaryEps = 0.0000001

        assertByOffset(function(x, secondaryEps), result, Offset.offset(eps))
    }
}
