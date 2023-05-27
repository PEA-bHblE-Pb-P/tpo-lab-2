@file:Suppress("MagicNumber")
package functions.system

import functions.base.Cos
import java.io.BufferedWriter
import kotlin.math.abs

class Csc(
    private val sin: (Double, Double) -> Double = Sin(Cos()),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val sinVal = sin(x, eps * 0.1)

        val result = when {
            sinVal == 0.0 -> Double.NaN
            sinVal.isNaN() || abs(sinVal) < eps -> Double.POSITIVE_INFINITY
            else -> 1.0 / sinVal
        }
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
