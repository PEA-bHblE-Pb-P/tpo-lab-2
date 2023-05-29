@file:Suppress("MagicNumber")
package functions.system

import functions.base.Cos
import util.CsvUtils.writeRounded
import java.io.BufferedWriter
import kotlin.math.abs
import kotlin.math.sqrt

class Sin(
    private val cos: (Double, Double) -> Double = Cos(),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val xInit: Double = x
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) {
            return Double.NaN
        }
        var normalizedX = x
        while (normalizedX < 0) normalizedX += 2 * Math.PI
        while (normalizedX > 2 * Math.PI) normalizedX -= 2 * Math.PI
        val cosVal = cos(xInit, eps)
        val result: Double = if (normalizedX > Math.PI) {
            -1 * sqrt(1 - cosVal * cosVal)
        } else {
            sqrt(1 - cosVal * cosVal)
        }
        return when {
            abs(result) <= eps -> 0.0
            else -> result
        }.also { res ->
            csvLogger?.writeRounded(x,res)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
