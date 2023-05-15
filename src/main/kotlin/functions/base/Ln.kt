package functions.base

import java.io.BufferedWriter
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.math.pow


class Ln(
    private val csvLogger: BufferedWriter? = null
) : (Double, Double) -> Double {
    override fun invoke(x: Double, eps: Double): Double {
        val result = when {
            x.isNaN() || x < 0 -> Double.NaN
            x == 0.0 -> Double.NEGATIVE_INFINITY
            x == 1.0 -> 0.0
            x <= 2 -> calcLnByMercatorSeries(x - 1, eps)
            x == Double.POSITIVE_INFINITY -> Double.POSITIVE_INFINITY
            else -> calcLnEasy(x, eps)
        }
        return result.also {
            csvLogger?.write("$x,$result")
            csvLogger?.newLine()
            csvLogger?.flush()
        }
    }

    /**
     * [wiki](https://ru.wikipedia.org/wiki/%D0%A0%D1%8F%D0%B4_%D0%9C%D0%B5%D1%80%D0%BA%D0%B0%D1%82%D0%BE%D1%80%D0%B0)
     */
    private fun calcLnByMercatorSeries(x: Double, eps: Double): Double {
        val decX = BigDecimal.valueOf(x)
        val decEps = BigDecimal.valueOf(eps)
        var i = 1
        var prev = BigDecimal.valueOf(0.0)
        var curr = BigDecimal.valueOf(0.0)
        var diff = BigDecimal.valueOf(Double.MAX_VALUE)
        var pref = BigDecimal.valueOf(-1.0)
        while (diff > decEps) {
            pref *= BigDecimal.valueOf(-1.0)
            curr += pref.div(BigDecimal.valueOf(1.0 * i)).times(decX.pow(i))
            diff = (curr - prev).abs()
            prev = curr
            i++
        }
        return curr.toDouble()
    }

    /**
     * https://wikimedia.org/api/rest_v1/media/math/render/svg/ffded915cdc900c0944340217624650781bc6fbd
     * x = (1+x2)/(1-x2) => x-x*x2 = 1+x2 => x2*(-1-x)=-x+1 => x2=(-x+1)/(-1-x)=> x2=(x-1)/(1+x)
     */
    private fun calcLnEasy(x: Double, eps: Double): Double {
        val x2 = (x-1).div(1+x)
        var i = 0
        var prev = 0.0
        var curr = 0.0
        var diff = Double.MAX_VALUE
        while (diff > eps) {
            val j = 2*i+1
            curr += x2.pow(j).div(j)
            diff = abs(curr - prev)
            prev = curr
            i++
        }
        return curr*2
    }

}