@file:Suppress("MagicNumber")
package functions.system

import functions.base.Cos
import modules.BaseModule
import modules.SystemModuleImpl
import java.io.BufferedWriter
import kotlin.math.abs

class Cot(
    private val cos: (Double, Double) -> Double,
    private val sin: (Double, Double) -> Double,
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val cosVal = cos(x, eps * 0.1)
        val sinVal = sin(x, eps * 0.1)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || abs(sinVal) < eps) {
            Double.NaN
        } else {
            cosVal / sinVal
        }
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
