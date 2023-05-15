@file:Suppress("MagicNumber")
package functions.system

import functions.base.Ln
import modules.BaseModule
import java.io.BufferedWriter

class Log(
    private val ln: (Double, Double) -> Double,
    private val csvLogger: BufferedWriter? = null
) : (Double, Double, Double) -> Double {
    override fun invoke(a: Double, b: Double, eps: Double): Double {
        return ln(b, eps * 0.2) / ln(a, eps * 0.2).also { res ->
            csvLogger?.write("$a,$b,$res")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
