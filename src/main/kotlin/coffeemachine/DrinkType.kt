package coffeemachine

import java.math.BigDecimal

enum class DrinkType(val value: String, val price: BigDecimal = BigDecimal.ZERO) {
    COFFEE("C", 0.6.toBigDecimal()), TEA("T", 0.4.toBigDecimal()), CHOCOLATE("H", 0.5.toBigDecimal()),
}