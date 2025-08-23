package coffeemachine.service

import coffeemachine.Order

class OrderToDrinkMakerProtocolTranslator : TranslateOrderToDrinkMakerProtocol {
    override fun translate(order: Order): String {
        return "${order.drinkType.value}:${if (order.numberOfSugarCubes > 0) order.numberOfSugarCubes else ""}:${if (order.isStickAdded) 0 else ""}"
    }

    override fun translate(message: String): String {
        return "M:$message"
    }

}