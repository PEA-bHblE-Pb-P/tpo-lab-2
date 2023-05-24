@file:Suppress("MagicNumber", "LongParameterList")
package modules

import functions.base.Cos
import functions.base.Ln
import functions.system.*
import java.io.BufferedWriter
import kotlin.math.pow

class SystemModuleImpl(
    val cos: (Double, Double) -> Double = Cos(),
    val ln: (Double, Double) -> Double = Ln(),
    override val cot: (Double, Double) -> Double = Cot(cos, Sin(cos)),
    override val tan: (Double, Double) -> Double = Tan(cos, Sin(cos)),
    override val sin: (Double, Double) -> Double = Sin(cos),
    override val log: (Double, Double, Double) -> Double = Log(Ln()),
    override val csc: (Double, Double) -> Double = Csc(),
    private val csvLogger: BufferedWriter? = null,
) : SystemModule {

    override val system: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val result = if (x <= 0) {
                (((((tan(x, eps).pow(3)) / (csc(x, eps) + sin(x, eps))) - cot(x, eps)).pow(3)) / csc(x, eps))
            } else {
                (((((log(10.0, x.pow(2), eps)) + (log(10.0, x.pow(3), eps)))
                    - log(3.0, x, eps)) - (log(3.0, x, eps) - log(10.0, x, eps))).pow(3))

            }
            return result.also {
                csvLogger?.write("%.5f".format(x) + "\t" + "%.5f".format(result))
                csvLogger?.newLine()
                csvLogger?.flush()
            }
        }
}
