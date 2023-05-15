@file:Suppress("MagicNumber", "LongParameterList")
package modules

import functions.base.Cos
import functions.base.Ln
import functions.system.Cot
import functions.system.Log
import functions.system.Sin
import functions.system.Tan
import java.io.BufferedWriter
import kotlin.math.pow

class SystemModuleImpl(
    val cos: (Double, Double) -> Double = Cos(),
    override val cot: (Double, Double) -> Double = Cot(cos, Sin(cos)),
    override val tan: (Double, Double) -> Double = Tan(cos, Sin(cos)),
    override val sin: (Double, Double) -> Double = Sin(cos),
    override val log: (Double, Double, Double) -> Double = Log(Ln()),
    val ln: (Double, Double) -> Double = Ln(),
    private val csvLogger: BufferedWriter? = null
) :
    SystemModule {

    override val function: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            val result = if (x <= 0) {
                (((((tan(x, eps) - cot(x, eps)) / cot(x, eps)).pow(3)) + cos(x, eps)).pow(2))
            } else {
                ((((log(3.0, x, eps) * log(10.0, x, eps) + log(5.0, x, eps))
                        + (log(3.0, x, eps) - log(10.0, x, eps))) / log(2.0, x, eps))
                        + (log(2.0, x, eps) + (ln(x, eps) + log(2.0, x, eps))))

            }
            return result.also {
                csvLogger?.write("%.5f".format(x) + "\t" + "%.5f".format(result))
                csvLogger?.newLine()
                csvLogger?.flush()
            }
        }
}
