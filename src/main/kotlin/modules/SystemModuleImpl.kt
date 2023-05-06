package modules

class SystemModuleImpl(private val baseModule: BaseModule): SystemModule {
    override val cot: (Double) -> Double
        get() = TODO("Not yet implemented")
    override val csc: (Double) -> Double
        get() = TODO("Not yet implemented")
    override val sin: (Double) -> Double
        get() = TODO("Not yet implemented")
    override val tan: (Double) -> Double
        get() = TODO("Not yet implemented")
    override val log: (Double, Double) -> Double
        get() = { a, b -> baseModule.ln(b) / baseModule.ln(a) }
}