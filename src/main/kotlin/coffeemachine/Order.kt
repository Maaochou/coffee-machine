package coffeemachine

import java.math.BigDecimal

data class Order(
    val drinkType: DrinkType,
    val numberOfSugarCubes: Int = 0,
    val paidAmount: BigDecimal = BigDecimal.ZERO
) {
    val isStickAdded: Boolean = numberOfSugarCubes > 0
}