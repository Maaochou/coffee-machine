package coffeemachine.service

import coffeemachine.domain.DrinkType
import coffeemachine.domain.Order
import java.math.BigDecimal

class OrderProcessor(
    val makeDrink: MakeDrink = DrinkMaker(),
    val drinkMakerProtocolCodec: DrinkMakerProtocolCodec = DrinkMakerProtocolCodecImpl(),
    val drinkPrices: Map<DrinkType, BigDecimal> = mapOf(
        DrinkType.T to 0.4.toBigDecimal(),
        DrinkType.C to 0.6.toBigDecimal(),
        DrinkType.H to 0.5.toBigDecimal(),
        DrinkType.O to 0.6.toBigDecimal(),
    )
) : ProcessOrder {

    override fun processOrder(order: Order): Any {

        val drinkPrice =
            drinkPrices[order.type] ?: throw IllegalArgumentException("Drink type not found in price matrice")
        if (order.paidAmount < drinkPrice) {
            return makeDrink.processInstructions(drinkMakerProtocolCodec.encode("Not enough money for drink, you're missing ${drinkPrice - order.paidAmount} euros"))
        }

        val instructions = drinkMakerProtocolCodec.encode(order)
        return makeDrink.processInstructions(instructions)
    }

}