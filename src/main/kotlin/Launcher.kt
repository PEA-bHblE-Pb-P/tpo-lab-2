import modules.LoggedBaseModuleImpl
import modules.SystemModuleImpl

fun main() {
    val base = LoggedBaseModuleImpl()
    val system = SystemModuleImpl(base)
    println("%.5f".format(system.function(0.832887045, 0.000001)))
    println("%.5f".format(system.function(0.0, 0.000001)))
    println("%.5f".format(system.function(-Math.PI, 0.000001)))
    println("%.5f".format(system.function(-2*Math.PI, 0.000001)))
    println("%.5f".format(system.function(-Math.PI/2, 0.000001)))
    println("%.5f".format(system.function(2*(3.14159 * (-4) - 1.10301), 0.000001)))
}