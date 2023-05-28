package functions.base

import util.CsvUtils.writeRounded
import java.io.BufferedWriter
import kotlin.math.absoluteValue
import kotlin.math.pow

class Ln(
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double =
        calcThatWorks(x, eps).also {
            csvLogger?.writeRounded(x,it)
            csvLogger?.newLine()
            csvLogger?.flush()
        }

    /**
     * based on
     * https://mathsolver.microsoft.com/ru/solve-problem/%60log_%7B%20e%20%20%7D(%7B%20-1%20%20%7D)
     */
    private fun calcThatWorks(x: Double, eps: Double): Double {
        val fastResult = when {
            x == Double.POSITIVE_INFINITY -> Double.POSITIVE_INFINITY
            x == 0.0 -> Double.NEGATIVE_INFINITY
            x == 1.0 -> 0.0
            x.isNaN() || x < 0.0 -> Double.NaN
            else -> null
        }
        if (fastResult != null)
            return fastResult

        val const = (x - 1).pow(2) / (x + 1).pow(2)
        var ans = 0.0
        var cur = (x - 1) / (x + 1)
        var step = 1

        while (cur.absoluteValue > eps) {
            ans += cur
            cur = (2 * step - 1) * cur * const / (2 * step + 1)
            step++
        }

        return ans * 2
    }
}
