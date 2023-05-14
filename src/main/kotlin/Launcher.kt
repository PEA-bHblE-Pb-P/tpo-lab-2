import modules.LoggedBaseModuleImpl
import modules.SystemModuleImpl

fun main() {
    val base = LoggedBaseModuleImpl()
    val system = SystemModuleImpl(base)
    println(system.function(1.0, 1.0))
}