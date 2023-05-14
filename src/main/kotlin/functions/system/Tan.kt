package functions.system

import modules.BaseModule
import java.io.BufferedWriter

class Tan(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val sinVal = baseModule.cos(x, eps)
        val cosVal = baseModule.cos(x, eps)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || cosVal == 0.0)
            Double.NaN else sinVal / cosVal
        return result.also {
            csvLogger?.use {
                it.write("$x,$result")
                it.newLine()
            }
        }
    }
}