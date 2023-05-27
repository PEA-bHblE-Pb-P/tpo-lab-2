package functions

import functions.base.Cos
import functions.base.Ln
import functions.system.*
import modules.SystemModuleImpl
import org.assertj.core.data.Offset.offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.Round.roundStLibValue
import util.assertByOffset
import kotlin.math.*

class GodTest {
    private val eps = 0.00000000001
    private val offset = offset(0.000000001)
    private val range = (-1000..1000).toList()

    @TestFactory
    fun testSinByStLib(): Collection<DynamicTest> = Sin(cos = {x, _ -> cos(x) }).let { sin ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, sin($x), eps=${offset.value}") {
                assertByOffset(
                    sin(x, eps),
                    sin(x),
                    offset
                )
            }
        }
    }

    @TestFactory
    fun testCosByStLib(): Collection<DynamicTest> = Cos().let { cos ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, cos($x), eps=${offset.value}") {
                assertByOffset(
                    cos(x, eps),
                    cos(x),
                    offset
                )
            }
        }
    }

    @TestFactory
    fun testLnByStLib(): Collection<DynamicTest> = Ln().let { ln ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, ln($x), eps=${offset.value}") {
                assertByOffset(
                    ln(x, eps),
                    ln(x),
                    offset
                )
            }
        }
    }

    @TestFactory
    fun testTanByStLib(): Collection<DynamicTest> = Tan().let { tan ->
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, tan($x), eps=${offset.value}") {
                assertByOffset(
                    tan(x, eps),
                    tan(x),
                    offset
                )
            }
        }
    }

    @TestFactory
    fun testCotByStLib(): Collection<DynamicTest> = Cot().let { cot ->
        val cotOffset = offset(offset.value*100)
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, cot($x), eps=${cotOffset.value}") {
                assertByOffset(
                    cot(x, eps),
                    cos(x) / sin(x),
                    cotOffset
                )
            }
        }
    }

    @TestFactory
    fun testCscByStLib(): Collection<DynamicTest> = Csc().let { csc ->
        val cscOffset = offset(offset.value*100)
        range.map {
            val x = it / 10.0
            DynamicTest.dynamicTest("$x, csc($x), eps=${cscOffset.value}") {
                assertByOffset(
                    csc(x, eps),
                    1.0 / sin(x).roundStLibValue(),
                    cscOffset
                )
            }
        }
    }

    @TestFactory
    fun testLogByStLib(): Collection<DynamicTest> = Log().let { log ->
        range.map {
            val x = it / 10.0
            range.map { b ->
                val base = b / 10.0
                DynamicTest.dynamicTest("$x, log$base ($x), eps=${offset.value}") {
                    assertByOffset(
                        log(base, x, eps),
                        log(x, base),
                        offset
                    )
                }
            }
        }.flatten()
    }

    @TestFactory
    fun testSystemByStLib(): Collection<DynamicTest> {
        val libOffset = offset.value * 1000
        val systemModuleImplByStLib = SystemModuleImpl(
            cos = { x, _ -> cos(x).roundStLibValue() },
            ln = { x, _ -> ln(x).roundStLibValue() },
            cot = { x, _ -> cos(x).roundStLibValue() / sin(x).roundStLibValue() },
            tan = { x, _ -> tan(x).roundStLibValue() },
            sin = { x, _ -> sin(x).roundStLibValue() },
            log = { base, x, _ -> log(x, base).roundStLibValue() }
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
                assertByOffset(
                    systemModuleImpl.system(x, eps),
                    systemModuleImplByStLib.system(x, eps),
                    offset(libOffset)
                )
            }
        }
    }
}