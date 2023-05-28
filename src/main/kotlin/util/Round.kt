package util

import kotlin.math.roundToLong

object Round {
    fun Double.rounded() =
        if (!this.isNaN() && this.isFinite())
            (this * 1E7).roundToLong() / 1E7
        else
            this

    fun Double.roundStLibValue() =
        if (this.isNaN() || this.isInfinite())
            this
        else (this * 1E14).roundToLong() / 1E14
}