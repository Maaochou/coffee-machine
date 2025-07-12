package coffeemachine.service

import prep.mao.coffeemachine.DrinkType
import java.math.BigDecimal

interface CheckPrice {

    fun isPaidAmountSufficient(drinkType: DrinkType, paidAmount: BigDecimal): Boolean

    fun computeMissingAmountString(drinkType: DrinkType, paidAmount: BigDecimal): String
}