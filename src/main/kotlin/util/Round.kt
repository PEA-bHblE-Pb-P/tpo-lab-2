package util

import kotlin.math.roundToLong

object Round {
    fun Double.rounded() =
        if (!this.isNaN() && this.isFinite())
            (this * 1E9).roundToLong() / 1E9
        else
            this

    fun Double.roundStLibValue() =
        if (this.isNaN() || this.isInfinite())
            this
        else (this * 1E13).roundToLong() / 1E13
}