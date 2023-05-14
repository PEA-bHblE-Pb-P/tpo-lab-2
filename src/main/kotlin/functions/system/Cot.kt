package functions.system

import modules.BaseModule
import java.io.BufferedWriter

class Cot(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val cosVal = baseModule.cos(x, eps)
        val sinVal = baseModule.cos(x, eps)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || sinVal == 0.0)
            Double.NaN
        else cosVal / sinVal
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}