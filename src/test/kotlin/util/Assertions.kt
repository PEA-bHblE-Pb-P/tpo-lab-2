package util

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import kotlin.math.abs

fun assertByOffset(actual: Double, expected: Double, offset: Offset<Double>): AbstractDoubleAssert<*> {
    return if (actual.isNaN() || expected.isNaN())
        assertThat(actual).isCloseTo(expected, offset)
    else assertThat(abs((actual - expected) / (if (expected == 0.0) 1.0 else expected )))
        .isLessThanOrEqualTo(offset.value)
}