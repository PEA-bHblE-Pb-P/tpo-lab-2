package modules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LogTest: StubbedBaseModuleTest() {
    @Test
    fun log() {
        assertThat(systemModule.log(50.0, 2.0)).isEqualTo(5.64385618977472)
    }
}