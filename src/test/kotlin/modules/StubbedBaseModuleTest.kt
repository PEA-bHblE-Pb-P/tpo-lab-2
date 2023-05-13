package modules

open class StubbedBaseModuleTest {
    private val baseModule = TableBaseModule()
    val systemModule = SystemModuleImpl(baseModule)
}