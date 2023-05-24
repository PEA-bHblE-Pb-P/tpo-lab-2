package util

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset

fun assertByOffset(actual: Double, expected: Double, offset: Offset<Double>): AbstractDoubleAssert<*> {
    return if (actual.isNaN() || expected.isNaN())
        assertThat(actual).isCloseTo(expected, offset)
    else assertThat((actual - expected) / expected)
        .isLessThanOrEqualTo(offset.value)
}