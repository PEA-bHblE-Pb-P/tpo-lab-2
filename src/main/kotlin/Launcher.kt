@file:Suppress("MagicNumber")
import functions.base.Cos
import functions.base.Ln
import functions.system.*
import modules.LoggedBaseModuleImpl
import modules.SystemModuleImpl
import util.CsvUtils

fun main() {
    val base = LoggedBaseModuleImpl()
    val system = SystemModuleImpl(
        Cos(CsvUtils.csvFileWriter("cos.csv", "x,cos(x)")),
        Ln(CsvUtils.csvFileWriter("Ln.csv", "x,ln(x)")),
        Cot(
            base.cos,
            Sin(base.cos, CsvUtils.csvFileWriter("cot.sin.csv", "x,sin(x)")),
            CsvUtils.csvFileWriter("cot.csv", "x,cot(x)")
        ),
        Tan(
            base.cos,
            Sin(base.cos, CsvUtils.csvFileWriter("tan.sin.csv", "x,sin(x)")),
            CsvUtils.csvFileWriter("tan.csv", "x,tan(x)")
        ),
        Sin(base.cos, CsvUtils.csvFileWriter("sin.csv", "x,sin(x)")),
        Log(base.ln, CsvUtils.csvFileWriter("log.csv", "x,log(x)")),
        Csc(Sin(base.cos)),
        CsvUtils.csvFileWriter("System.csv", "x,system(x)"),
    )
    var start = -25.0
    var end = 25.0
    var step = 0.001
    var i = start
    while (i < end) {
        system.system(i, 0.01)
        i += step
    }

    start = 0.0
    end = 0.2
    step = 0.0001
    i = start
    while (i < end) {
        system.system(i, 0.01)
        i += step
    }
}
