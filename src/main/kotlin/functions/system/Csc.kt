@file:Suppress("MagicNumber")
package functions.system

import functions.base.Cos
import util.CsvUtils.writeRounded
import java.io.BufferedWriter
import kotlin.math.abs

class Csc(
    private val sin: (Double, Double) -> Double = Sin(Cos()),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val sinVal = sin(x, eps * 0.1)

        val result = when {
            sinVal == 0.0 -> if (x >= 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
            sinVal.isNaN() || abs(sinVal) < eps -> Double.NaN
            else -> 1.0 / sinVal
        }
        return result.also {
            csvLogger?.writeRounded(x,result)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
