package prep.mao.coffeemachine

import coffeemachine.CheckPrice
import coffeemachine.DrinkMaker
import coffeemachine.MakeDrink
import coffeemachine.PriceChecker

class OrderTaker(
    val drinkMakerProtocolTranslator: TranslateDrinkMakerProtocol = DrinkMakerProtocolTranslator(),
    val drinkMaker: MakeDrink = DrinkMaker(),
    val priceChecker: CheckPrice = PriceChecker()
) : TakeOrder {

    override fun takeOrder(order: Order): String {
        if (!priceChecker.isPaidAmountSufficient(order.drinkType, order.paidAmount))
            return priceChecker.computeMissingAmountString(order.drinkType, order.paidAmount)

        val commande = drinkMakerProtocolTranslator.translateOrder(order)
        return drinkMaker.processCommand(commande)
    }


}
