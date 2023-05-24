package functions

import functions.base.Cos
import functions.base.Ln
import functions.system.Cot
import functions.system.Log
import functions.system.Sin
import functions.system.Tan
import modules.SystemModuleImpl
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.math.*

class GodTest {
    private val eps = 0.00000000001
    private val offset = Offset.offset(0.000000001)
    private val range = (-1000..1000).toList()

    @TestFactory
    fun testSinByStLib(): Collection<DynamicTest> = Sin(cos = {x, _ -> cos(x) }).let { sin ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, sin($x), eps=$offset") {
                assertThat(sin(x, eps)).isCloseTo(kotlin.math.sin(x), offset)
            }
        }
    }

    @TestFactory
    fun testCosByStLib(): Collection<DynamicTest> = Cos().let { cos ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, cos($x), eps=$offset") {
                assertThat(cos(x, eps)).isCloseTo(cos(x), offset)
            }
        }
    }

    @TestFactory
    fun testLnByStLib(): Collection<DynamicTest> = Ln().let { ln ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, ln($x), eps=$offset") {
                assertThat(ln(x, eps)).isCloseTo(ln(x), offset)
            }
        }
    }

    @TestFactory
    fun testTanByStLib(): Collection<DynamicTest> = Tan().let { tan ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, tan($x), eps=$offset") {
                assertThat(tan(x, eps)).isCloseTo(tan(x), offset)
            }
        }
    }

    @TestFactory
    fun testCotByStLib(): Collection<DynamicTest> = Cot().let { cot ->
        val cotOffset = Offset.offset(offset.value*100)
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, tan($x), eps=$cotOffset") {
                assertThat(cot(x, eps)).isCloseTo(cos(x) / sin(x), cotOffset)
            }
        }
    }

    @TestFactory
    fun testSystemByStLib(): Collection<DynamicTest> {
        val libOffset = offset.value * 1000
        val systemModuleImplByStLib = SystemModuleImpl(
            cos = { x, _ -> cos(x) },
            ln = { x, _ -> ln(x) },
            cot = { x, _ -> cos(x) / sin(x) },
            tan = { x, _ -> tan(x) },
            sin = { x, _ -> sin(x) },
            log = { base, x, _ -> log(x, base) }
        )
        val systemModuleImpl = SystemModuleImpl(
            cos = Cos(),
            ln = Ln(),
            cot = Cot(),
            tan = Tan(),
            sin = Sin(),
            log = Log()
        )
        return range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, fun($x), eps=$libOffset") {
                assertThat(
                    (systemModuleImpl.system(x, eps) - systemModuleImplByStLib.system(x, eps))
                            / systemModuleImplByStLib.system(x, eps)).isLessThanOrEqualTo(libOffset)
            }
        }
    }
}