package prep.mao.coffeemachine

class DrinkMakerProtocolTranslator : TranslateDrinkMakerProtocol {
    override fun translateOrder(order: Order): String {
        val drinkTypeChar: Char = when (order.drinkType) {
            DrinkType.Coffee -> 'C'
            DrinkType.Tea -> 'T'
            DrinkType.Chocolate -> 'H'
        }
        return "$drinkTypeChar:${if (order.nbrOfSugar > 0) order.nbrOfSugar else ""}:${if (order.isStickAdded) 0 else ""}"
    }

    override fun translateMessage(message: String): String {
        return "M:$message"
    }
}