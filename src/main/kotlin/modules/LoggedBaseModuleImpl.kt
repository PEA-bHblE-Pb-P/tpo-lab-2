package modules

import functions.base.Cos
import functions.base.Ln
import util.CsvUtils.csvFileWriter

class LoggedBaseModuleImpl(
    override val ln: (Double, Double) -> Double = Ln(csvFileWriter("ln", "x,ln(x)")),
    override val cos: (Double, Double) -> Double = Cos(csvFileWriter("cos", "x,cos(x)"))
) : BaseModule