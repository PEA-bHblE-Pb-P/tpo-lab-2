@file:Suppress("MagicNumber")

package modules

import functions.system.*
import kotlin.math.pow

class SystemModuleImpl(private val baseModule: BaseModule) : SystemModule {
    override val cot = Cot(baseModule)
    override val tan = Tan(baseModule)
    override val csc = Csc(baseModule)
    override val sin = Sin(baseModule)
    override val log = Log(baseModule)

    override val function: (Double, Double) -> Double
        get() = fun(x: Double, eps: Double): Double {
            return if (x <= 0) {
                (((((tan(x, eps) - cot(x, eps)) / cot(x, eps)).pow(3)) + baseModule.cos(x, eps)).pow(2))
            } else {
                ((((log(3.0, x, eps) * log(10.0, x, eps) + log(5.0, x, eps))
                        + (log(3.0, x, eps) - log(10.0, x, eps))) / log(2.0, x, eps))
                        + (log(2.0, x, eps) + (baseModule.ln(x, eps) + log(2.0, x, eps))))

            }
        }
}
