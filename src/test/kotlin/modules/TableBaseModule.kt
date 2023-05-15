package modules

import util.CsvUtils.readCsvFileValues

class TableBaseModule : BaseModule {
    private val lnTableValues = readCsvFileValues("/ln.csv")
    private val cosTableValues = readCsvFileValues("/cos.csv")
    private val sinTableValues = readCsvFileValues("/sin.csv")
    private val tanTableValues = readCsvFileValues("/tan.csv")
    private val cotTableValues = readCsvFileValues("/cot.csv")
    private val cscTableValues = readCsvFileValues("/csc.csv")
    private val log2TableValues = readCsvFileValues("/log2.csv")
    private val log3TableValues = readCsvFileValues("/log3.csv")
    private val log5TableValues = readCsvFileValues("/log5.csv")
    private val log10TableValues = readCsvFileValues("/log10.csv")

    override val ln: (Double, Double) -> Double
        get() = { x, _ -> lnTableValues[x] ?: throw IllegalStateException("No table value for $x") }

    override val cos: (Double, Double) -> Double
        get() = { x, _ -> cosTableValues[x] ?: throw IllegalStateException("No table value for $x") }

    val sin: (Double, Double) -> Double
        get() = { x, _ -> sinTableValues[x] ?: throw IllegalStateException("No table value for $x") }
    val tan: (Double, Double) -> Double
        get() = { x, _ -> tanTableValues[x] ?: throw IllegalStateException("No table value for $x") }
    val cot: (Double, Double) -> Double
        get() = { x, _ -> cotTableValues[x] ?: throw IllegalStateException("No table value for $x") }
    val csc: (Double, Double) -> Double
        get() = { x, _ -> cscTableValues[x] ?: throw IllegalStateException("No table value for $x") }
    val log: (Double, Double, Double) -> Double
        get() = { a, b, _ ->
            when (a) {
                2.0 -> log2TableValues[b] ?: throw IllegalStateException("No table value for $b")
                3.0 -> log3TableValues[b] ?: throw IllegalStateException("No table value for $b")
                5.0 -> log5TableValues[b] ?: throw IllegalStateException("No table value for $b")
                10.0 -> log10TableValues[b] ?: throw IllegalStateException("No table value for $b")
                else -> {
                    throw IllegalStateException("No table value for a = $a")
                }
            }
        }


}
