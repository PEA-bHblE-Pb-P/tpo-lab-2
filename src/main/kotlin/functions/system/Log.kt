@file:Suppress("MagicNumber")
package functions.system

import functions.base.Ln
import util.CsvUtils.writeRounded3
import java.io.BufferedWriter

class Log(
    private val ln: (Double, Double) -> Double = Ln(),
    private val csvLogger: BufferedWriter? = null
) : (Double, Double, Double) -> Double {
    override fun invoke(a: Double, b: Double, eps: Double): Double {

        return ln(b, eps * 0.2) / ln(a, eps * 0.2).also { res ->
            csvLogger?.writeRounded3(a,b,res)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
