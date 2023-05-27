package util

import kotlin.math.roundToLong

object Round {
    fun Double.roundStLibValue() = (this * 1E14).roundToLong() / 1E14
}