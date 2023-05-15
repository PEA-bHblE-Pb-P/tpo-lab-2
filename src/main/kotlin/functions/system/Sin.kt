package functions.system

import modules.BaseModule
import java.io.BufferedWriter
import kotlin.math.abs
import kotlin.math.sqrt

class Sin(
    private val baseModule: BaseModule,
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
        val result: Double = if (normalizedX > Math.PI) {
            -1 * sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
        } else {
            sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
        }
        return when {
            abs(result) > 1 -> Double.NaN
            abs(result) <= eps -> 0.0
            else -> result
        }.also { res ->
            csvLogger?.write("$x,$res")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}