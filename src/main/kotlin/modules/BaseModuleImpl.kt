package modules

import kotlin.math.abs
import kotlin.math.pow

class BaseModuleImpl: BaseModule {
    override val ln: (Double, Double) -> Double
        get() = TODO()

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