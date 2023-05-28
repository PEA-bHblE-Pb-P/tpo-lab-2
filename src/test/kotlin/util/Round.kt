package util

import kotlin.math.roundToLong

object Round {
    fun Double.roundStLibValue() =
        if (this.isNaN() || this.isInfinite())
            this
        else (this * 1E14).roundToLong() / 1E14
}