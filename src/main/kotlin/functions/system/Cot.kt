package functions.system

import modules.BaseModule
import modules.SystemModuleImpl
import java.io.BufferedWriter
import kotlin.math.abs

class Cot(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val cosVal = baseModule.cos(x, eps * 0.1)
        val sinVal = SystemModuleImpl(baseModule = baseModule).sin(x, eps * 0.1)
        val result = if (cosVal.isNaN() || sinVal.isNaN() || abs(sinVal) < eps) {
            Double.NaN
        } else {
            cosVal / sinVal
        }
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}