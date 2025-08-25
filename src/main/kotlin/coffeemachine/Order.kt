package coffeemachine

import java.math.BigDecimal

data class Order(
    val drinkType: DrinkType,
    val numberOfSugarCubes: Int = 0,
    val paidAmount: BigDecimal = BigDecimal.ZERO,
    val isExtraHot: Boolean = false
) {
    init {
        require(numberOfSugarCubes >= 0) { "Number of sugar cubes should be more or equal to 0" }
        require(paidAmount >= BigDecimal.ZERO) { "Paid amount should be more or equal to 0" }
        require(!isExtraHot || (isExtraHot && drinkType.canBeSoldExtraHot)) { "The drink type ${drinkType.name} can't be sold extra hot" }
    }

    val isStickAdded: Boolean = numberOfSugarCubes > 0
}