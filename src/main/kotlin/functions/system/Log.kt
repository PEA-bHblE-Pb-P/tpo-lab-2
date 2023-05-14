package functions.system

import modules.BaseModule
import java.io.BufferedWriter

class Log(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
): (Double, Double, Double) -> Double {
    override fun invoke(a: Double, b: Double, eps: Double): Double {
        return baseModule.ln(b, eps) / baseModule.ln(a, eps).also { res ->
            csvLogger?.use {
                it.write("$a,$b,$res")
                it.newLine()
            }
        }
    }
}