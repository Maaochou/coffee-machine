package coffeemachine.service

import coffeemachine.Order
import coffeemachine.helper.DrinkMakerProtocolHelper

class OrderToDrinkMakerProtocolTranslator(val checkOrderPaidAmount: CheckOrderPaidAmount = OrderPaidAmountChecker()) :
    TranslateOrderToDrinkMakerProtocol {

    override fun translate(order: Order): String {

        if (checkOrderPaidAmount.isPaidAmountValid(order)) return DrinkMakerProtocolHelper.formatOrder(order)

        return DrinkMakerProtocolHelper.formatMessage(
            DrinkMakerProtocolHelper.formatMissingAmount(
                order.drinkType.price.minus(
                    order.paidAmount
                )
            )
        )
    }

}