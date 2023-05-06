package modules

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SystemModuleImplTest {
    private val baseModule = TableBaseModule()
    private val systemModule = SystemModuleImpl(baseModule)

    @Test
    fun log() {
        assertEquals(systemModule.log(50.0, 2.0), 5.64385618977472)
    }
}