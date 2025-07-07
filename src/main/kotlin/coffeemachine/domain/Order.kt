package coffeemachine.domain

import java.math.BigDecimal

data class Order(
    val type: DrinkType, val sugar: Int = 0, val paidAmount: BigDecimal = BigDecimal.ZERO, val extraHot: Boolean = false
)