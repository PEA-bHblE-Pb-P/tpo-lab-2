package functions

import functions.base.Ln
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import util.assertByOffset

class LnInternalsTest {
    private val ln = Ln()
    private val eps = 0.00001
    private val offset = Offset.offset(eps)

    @Test
    fun `ln e should be equal to 1`() {
        assertByOffset(ln(Math.E, eps), 1.0, offset)
    }

    @Test
    fun `ln 1 should be equal to 0`() {
        assertByOffset(ln(1.0, eps), 0.0, offset)
    }
}