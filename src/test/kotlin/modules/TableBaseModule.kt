package modules

import util.CsvUtils.readCsvFileValues
import util.Round.roundStLibValue

class TableBaseModule(folder: String = "") : BaseModule {
    private val lnTableValues = readCsvFileValues("$folder/ln.csv")
    private val cosTableValues = readCsvFileValues("$folder/cos.csv")
    private val sinTableValues = readCsvFileValues("$folder/sin.csv")
    private val tanTableValues = readCsvFileValues("$folder/tan.csv")
    private val cotTableValues = readCsvFileValues("$folder/cot.csv")
    private val cscTableValues = readCsvFileValues("$folder/csc.csv")
    private val log3TableValues = readCsvFileValues("$folder/log3.csv")
    private val log10TableValues = readCsvFileValues("$folder/log10.csv")

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
    val csc: (Double, Double) -> Double
        get() = { x, _ -> cscTableValues[x.roundStLibValue()] ?: throw IllegalStateException("No table value for ${x.roundStLibValue()} in csc") }

    val log: (Double, Double, Double) -> Double
        get() = { a, b, _ ->
            when (a) {
                3.0 -> log3TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln3")
                10.0 -> log10TableValues[b.roundStLibValue()] ?: throw IllegalStateException("No table value for ${b.roundStLibValue()} in ln10")
                else -> {
                    throw IllegalStateException("No table value for a = $a")
                }
            }
        }


}
