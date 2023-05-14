package functions.system

import modules.BaseModule
import java.io.BufferedWriter
import kotlin.math.abs
import kotlin.math.sqrt

class Sin(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val xInit: Double = x
        var normalizedX = x % Math.PI * 2
        if (Double.POSITIVE_INFINITY == normalizedX || Double.NEGATIVE_INFINITY == normalizedX) {
            return Double.NaN
        }
        if (normalizedX < -Math.PI) {
            while (normalizedX < -Math.PI) normalizedX += 2 * Math.PI
        }
        if (normalizedX > Math.PI) {
            while (normalizedX > Math.PI) normalizedX -= 2 * Math.PI
        }
        val result: Double = if (normalizedX > Math.PI / 2 || normalizedX < -Math.PI / 2) {
            -1 * sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
        } else sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
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