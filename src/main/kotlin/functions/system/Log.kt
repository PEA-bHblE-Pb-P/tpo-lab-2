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
        if (a <= 0.0 || a == 1.0) return Double.NaN

        val lnB = ln(b, eps * 0.2)
        val lnA = ln(a, eps * 0.2)

        if (lnA == 0.0) {
            return if (lnB.isInfinite()) lnB else Double.NaN
        }

        return lnB / lnA.also { res ->
            csvLogger?.writeRounded3(a,b,res)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
