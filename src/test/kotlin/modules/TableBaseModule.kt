package modules

import util.CsvUtils.readCsvFileValues
import util.Round.roundStLibValue

class TableBaseModule : BaseModule {
    private val lnTableValues = readCsvFileValues("/ln.csv")
    private val cosTableValues = readCsvFileValues("/cos.csv")
    private val sinTableValues = readCsvFileValues("/sin.csv")
    private val tanTableValues = readCsvFileValues("/tan.csv")
    private val cotTableValues = readCsvFileValues("/cot.csv")
    private val log2TableValues = readCsvFileValues("/log2.csv")
    private val log3TableValues = readCsvFileValues("/log3.csv")
    private val log5TableValues = readCsvFileValues("/log5.csv")
    private val log10TableValues = readCsvFileValues("/log10.csv")

    override val ln: (Double, Double) -> Double
        get() = { x, _ -> lnTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in ln") }

    override val cos: (Double, Double) -> Double
        get() = { x, _ -> cosTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in cos") }

    val sin: (Double, Double) -> Double
        get() = { x, _ -> sinTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in sin") }
    val tan: (Double, Double) -> Double
        get() = { x, _ -> tanTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in tan") }
    val cot: (Double, Double) -> Double
        get() = { x, _ -> cotTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in cot") }
    val log: (Double, Double, Double) -> Double
        get() = { a, b, _ ->
            when (a) {
                2.0 -> log2TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln2")
                3.0 -> log3TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln3")
                5.0 -> log5TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln5")
                10.0 -> log10TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln10")
                else -> {
                    throw IllegalStateException("No table value for a = $a")
                }
            }
        }


}
