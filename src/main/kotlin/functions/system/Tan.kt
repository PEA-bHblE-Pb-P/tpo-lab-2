package functions.system

import modules.BaseModule
import modules.SystemModuleImpl
import java.io.BufferedWriter
import kotlin.math.abs

class Tan(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val sinVal = SystemModuleImpl(baseModule=baseModule).sin(x, eps)
        val cosVal = baseModule.cos(x, eps)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || abs(cosVal) < eps)
            Double.NaN else sinVal / cosVal
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}