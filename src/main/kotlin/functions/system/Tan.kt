package functions.system

import functions.base.Cos
import java.io.BufferedWriter
import kotlin.math.abs

class Tan(
    private val cos: (Double, Double) -> Double = Cos(),
    private val sin: (Double, Double) -> Double = Sin(Cos()),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val sinVal = sin(x, eps)
        val cosVal = cos(x, eps)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || abs(cosVal) < eps) {
            Double.NaN
        } else {
            sinVal / cosVal
        }
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
