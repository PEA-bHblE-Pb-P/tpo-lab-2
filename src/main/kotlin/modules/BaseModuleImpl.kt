package modules

import functions.base.Cos
import functions.base.Ln

class BaseModuleImpl: BaseModule {
    override val ln: (Double, Double) -> Double
        get() = Ln()

    override val cos: (Double, Double) -> Double
        get() = Cos()
}
