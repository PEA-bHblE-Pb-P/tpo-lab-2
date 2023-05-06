package modules

import java.math.BigDecimal

interface BaseModule {
    val ln: (BigDecimal) -> BigDecimal
    val sin: (BigDecimal) -> BigDecimal
}