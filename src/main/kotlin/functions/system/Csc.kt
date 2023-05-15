package functions.system

import modules.BaseModule
import java.io.BufferedWriter
import kotlin.math.abs

class Csc(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val cosVal = baseModule.cos(x, eps)
        val result = if (cosVal.isNaN() || abs(cosVal) < eps) Double.NaN else 1 / cosVal
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}