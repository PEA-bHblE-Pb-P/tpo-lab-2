package functions.system

import modules.BaseModule
import java.io.BufferedWriter

class Log(
    private val baseModule: BaseModule,
    private val csvLogger: BufferedWriter? = null
) : (Double, Double, Double) -> Double {
    override fun invoke(a: Double, b: Double, eps: Double): Double {
        return baseModule.ln(b, eps * 0.1) / baseModule.ln(a, eps * 0.1).also { res ->
            csvLogger?.write("$a,$b,$res")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}