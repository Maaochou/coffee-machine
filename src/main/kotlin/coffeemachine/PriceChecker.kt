package coffeemachine

import prep.mao.coffeemachine.DrinkType
import java.math.BigDecimal

class PriceChecker(
    val drinkPricesMap: Map<DrinkType, BigDecimal> = mapOf(
        DrinkType.Coffee to 0.6.toBigDecimal(),
        DrinkType.Tea to 0.4.toBigDecimal(),
        DrinkType.Chocolate to 0.5.toBigDecimal(),
    )
) : CheckPrice {

    override fun isPaidAmountSufficient(
        drinkType: DrinkType,
        paidAmount: BigDecimal
    ): Boolean = paidAmount >= (drinkPricesMap[drinkType]
        ?: throw IllegalArgumentException("Cannot find drink type in prices matrix"))

    override fun computeMissingAmountString(
        drinkType: DrinkType,
        paidAmount: BigDecimal
    ): String {
        val itemPrice =
            drinkPricesMap[drinkType] ?: throw IllegalArgumentException("Cannot find drink type in prices matrix")
        return "You are missing the following amount : ${computeMissingAmount(paidAmount, itemPrice)} euro"
    }

    fun computeMissingAmount(
        paidAmount: BigDecimal,
        itemPrice: BigDecimal
    ): BigDecimal = itemPrice - paidAmount

}