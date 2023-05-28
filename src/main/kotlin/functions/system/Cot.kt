@file:Suppress("MagicNumber")
package functions.system

import functions.base.Cos
import util.CsvUtils.writeRounded
import java.io.BufferedWriter
import kotlin.math.abs

class Cot(
    private val cos: (Double, Double) -> Double = Cos(),
    private val sin: (Double, Double) -> Double = Sin(Cos()),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val cosVal = cos(x, eps * 0.1)
        val sinVal = sin(x, eps * 0.1)
        val result = when {
            (cosVal.isNaN() || sinVal.isNaN() || abs(sinVal) < eps) -> Double.POSITIVE_INFINITY
            else -> cosVal / sinVal
         }
        return result.also {
            csvLogger?.writeRounded(x,result)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
