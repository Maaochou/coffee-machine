package coffeemachine

import java.math.BigDecimal

enum class DrinkType(
    val value: String,
    val price: BigDecimal = BigDecimal.ZERO,
    val canBeSoldExtraHot: Boolean = false
) {
    COFFEE("C", 0.6.toBigDecimal(), true), TEA("T", 0.4.toBigDecimal(), true), CHOCOLATE(
        "H",
        0.5.toBigDecimal(),
        true
    ),
    ORANGE_JUICE(
        "O", 0.6.toBigDecimal(), false
    )
}