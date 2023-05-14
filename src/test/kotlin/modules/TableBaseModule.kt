package modules

import util.CsvUtils.readCsvFileValues

class TableBaseModule: BaseModule {
    private val lnTableValues = readCsvFileValues("/ln.csv")
    private val cosTableValues = readCsvFileValues("/cos.csv")

    override val ln: (Double, Double) -> Double
        get() = { x, _ -> lnTableValues[x] ?: throw IllegalStateException("No table value for $x") }

    override val cos: (Double, Double) -> Double
        get() ={ x, _ -> cosTableValues[x] ?: throw IllegalStateException("No table value for $x") }
}
