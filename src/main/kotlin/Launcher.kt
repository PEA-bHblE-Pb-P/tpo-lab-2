@file:Suppress("MagicNumber")
import functions.base.Cos
import functions.base.Ln
import functions.system.Cot
import functions.system.Log
import functions.system.Sin
import functions.system.Tan
import modules.LoggedBaseModuleImpl
import modules.SystemModuleImpl
import util.CsvUtils

fun main() {
    val base = LoggedBaseModuleImpl()
    val system = SystemModuleImpl(
        Cos(CsvUtils.csvFileWriter("cos", "x,cos(x)")),
        Cot(
            base.cos,
            Sin(base.cos, CsvUtils.csvFileWriter("cot.sin", "x,sin(x)")),
            CsvUtils.csvFileWriter("cot", "x,cot(x)")
        ),
        Tan(
            base.cos,
            Sin(base.cos, CsvUtils.csvFileWriter("tan.cos", "x,cos(x)")),
            CsvUtils.csvFileWriter("tan", "x,tan(x)")
        ),
        Sin(base.cos, CsvUtils.csvFileWriter("sin", "x,sin(x)")),
        Log(base.ln, CsvUtils.csvFileWriter("log", "x,log(x)")),
        Ln(CsvUtils.csvFileWriter("Ln", "x,ln(x)")),
        CsvUtils.csvFileWriter("System", "x,system(x)")
    )
    val start = -3.01
    val end = 3.1
    val step = 0.1
    var i = start
    while (i < end) {
        system.system(i, 0.01)
        i += step
    }
}
