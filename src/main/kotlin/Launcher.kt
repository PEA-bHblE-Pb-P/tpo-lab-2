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
        Ln(CsvUtils.csvFileWriter("_ln.csv", "x,ln(x)")),
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
        Csc(Sin(base.cos), CsvUtils.csvFileWriter("csc.csv", "x,log(x)")),
        CsvUtils.csvFileWriter("System.csv", "x,system(x)"),
    )
    var start = 0.0
    var end = 100.0
    var step = 0.01
    var i = 0
    while (start + i*step <= end) {
        system.system(start + i*step, 0.0000001)
        i += 1
    }


}
