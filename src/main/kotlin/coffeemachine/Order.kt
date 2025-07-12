package prep.mao.coffeemachine

import java.math.BigDecimal

data class Order(
    val drinkType: DrinkType,
    val nbrOfSugar: Int = 0,
    val isStickAdded: Boolean = false,
    val paidAmount: BigDecimal = BigDecimal.ZERO
)