package modules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.io.path.readLines

class LoggedBaseModuleImplTest {
    private val module = LoggedBaseModuleImpl()

    @Test
    fun `ln is logged`() {
        module.ln(0.0, 0.01)
        assertThat(Path("ln.csv").readLines())
            .isEqualTo(listOf("x,ln(x)","0.0,-Infinity"))
    }

    @Test
    fun `header is written once`() {
        module.ln(0.0, 0.01)
        module.ln(0.0, 0.01)
        assertThat(Path("ln.csv").readLines())
            .isEqualTo(listOf("x,ln(x)","0.0,-Infinity","0.0,-Infinity"))
    }
}