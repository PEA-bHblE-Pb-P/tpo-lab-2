package modules

import kotlin.math.abs
import kotlin.math.pow

class BaseModuleImpl: BaseModule {
    override val ln: (Double, Double) -> Double
        get() = { x, eps ->
            when {
                x.isNaN() || x < 0 -> Double.NaN
                x == 0.0 -> Double.NEGATIVE_INFINITY
                x == 1.0 -> 0.0
                x <= 2 -> calcLnByMercatorSeries(x-1, eps)
                x == Double.POSITIVE_INFINITY -> Double.POSITIVE_INFINITY
                else -> calcLnEasy(x, eps)
            }
        }

    /**
     * [wiki](https://ru.wikipedia.org/wiki/%D0%A0%D1%8F%D0%B4_%D0%9C%D0%B5%D1%80%D0%BA%D0%B0%D1%82%D0%BE%D1%80%D0%B0)
     */
    private fun calcLnByMercatorSeries(x: Double, eps: Double): Double {
        var i = 1
        var prev = 0.0
        var curr = 0.0
        var diff = Double.MAX_VALUE
        var pref = -1.0
        while (diff > eps) {
            pref *= -1.0
            curr += pref.div(i).times(x.pow(i))
            diff = abs(curr - prev)
            prev = curr
            i++
        }
        return curr
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

    /**
     * https://scask.ru/f_book_sm_math1.php?id=130
     */
    override val cos: (Double, Double) -> Double
        get() = { x, eps ->
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
                curr += pref.times(x2.pow(2*i)).div(fact)
                diff = abs(curr - prev)
                i++
                fact *= 2*i*(2*i-1)
            }

            curr
        }
}