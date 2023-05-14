@file:Suppress("MagicNumber")
package modules

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class SystemModuleImpl(private val baseModule: BaseModule) : SystemModule {
    override val cot: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val cosVal: Double = baseModule.cos(x, eps)
            val sinVal: Double = baseModule.cos(x, eps)
            return if (cosVal.isNaN() || sinVal.isNaN() || sinVal == 0.0) Double.NaN else cosVal / sinVal
        }
    override val tan: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val sinVal: Double = baseModule.cos(x, eps)
            val cosVal: Double = baseModule.cos(x, eps)
            return if (cosVal.isNaN() || sinVal.isNaN() || cosVal == 0.0) Double.NaN else sinVal / cosVal
        }
    override val csc: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val cosVal: Double = baseModule.cos(x, eps)
            return if (cosVal.isNaN() || cosVal == 0.0) Double.NaN else 1 / cosVal
        }
    override val sin: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val xInit: Double = x
            var normalizedX = x % Math.PI * 2
            if (Double.POSITIVE_INFINITY == normalizedX || Double.NEGATIVE_INFINITY == normalizedX) {
                return Double.NaN
            }
            if (normalizedX < -Math.PI) {
                while (normalizedX < -Math.PI) normalizedX += 2 * Math.PI
            }
            if (normalizedX > Math.PI) {
                while (normalizedX > Math.PI) normalizedX -= 2 * Math.PI
            }
            val result: Double = if (normalizedX > Math.PI / 2 || normalizedX < -Math.PI / 2) {
                -1 * sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
            } else sqrt(1 - baseModule.cos(xInit, eps) * baseModule.cos(xInit, eps))
            return when {
                abs(result) > 1 -> Double.NaN
                abs(result) <= eps -> 0.0
                else -> result
            }
        }
    override val log: (Double, Double) -> Double
        get() = fun(a: Double, b: Double): Double {
            return baseModule.ln(b, 0.0000000000000001) / baseModule.ln(a, 0.0000000000000001)
        }
    override val function: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            return if (x <= 0) {
                (((((tan(x, eps) - cot(x, eps)) / cot(x, eps)).pow(3)) + baseModule.cos(x, eps)).pow(2))
            } else {
                (((((log(3.0, x) * log(10.0, x)) + log(5.0, x)) + (log(3.0, x) - log(10.0, x))) / log(2.0, x))
                        + (log(2.0, x) + (baseModule.ln(x, eps) + baseModule.ln(2.0, x))))
            }
        }
}
