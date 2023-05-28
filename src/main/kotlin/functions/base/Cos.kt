@file:Suppress("MagicNumber")
package functions.base

import util.CsvUtils.writeRounded
import java.io.BufferedWriter
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.pow

class Cos(
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    /**
     * https://scask.ru/f_book_sm_math1.php?id=130
     */
    override fun invoke(x: Double, eps: Double): Double {
        val x2 = if (x > 0)
            x % (Math.PI * 2)
        else
            x % (-Math.PI * 2)

        var curr = 1.0
        var prev: Double
        var diff = Double.MAX_VALUE
        var i = 1
        var fact = 2.0
        var pref = 1.0
        while (diff > eps) {
            pref *= -1.0
            prev = curr
            curr += pref.times(x2.pow(2 * i)).div(fact)
            diff = abs(curr - prev)
            i++
            fact *= 2 * i * (2 * i - 1)
        }
        if ((curr.absoluteValue - 1.0).absoluteValue < eps) {
            if (curr < 0) {
                curr = -1.0
            } else {
                curr = 1.0
            }
        }
        return curr.also {
            csvLogger?.writeRounded(x,curr)
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }
}
