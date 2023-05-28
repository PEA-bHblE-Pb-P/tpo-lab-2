package util

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import kotlin.math.abs
import kotlin.math.absoluteValue

fun assertByOffset(actual: Double, expected: Double, offset: Offset<Double>): AbstractDoubleAssert<*> {
    return when {
        actual.isNaN() || expected.isNaN() || actual.isInfinite() || expected.isInfinite() -> {
            assertThat(actual).isCloseTo(expected, offset)
        }

        ((actual - expected).absoluteValue < offset.value) -> assertThat(0.0).isEqualTo(0.0)

        else -> assertThat(abs((actual - expected) / (if (expected == 0.0) 1.0 else expected)))
            .isLessThanOrEqualTo(offset.value)
    }
}