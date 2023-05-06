package modules

import util.CsvUtils.readCsvFileValues

class TableBaseModule: BaseModule {
    private val lnTableValues = readCsvFileValues("/ln.csv")
    private val cosTableValues = readCsvFileValues("/cos.csv")

    override val ln: (Double) -> Double
        get() = { x -> lnTableValues[x] ?: throw IllegalStateException("No table value for $x") }

    override val cos: (Double) -> Double
        get() ={ x -> cosTableValues[x] ?: throw IllegalStateException("No table value for $x") }
}