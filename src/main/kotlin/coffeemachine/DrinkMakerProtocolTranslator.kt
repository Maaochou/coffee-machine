package prep.mao.coffeemachine

class DrinkMakerProtocolTranslator : TranslateDrinkMakerProtocol {

    val drinkTypeMap = mapOf(
        DrinkType.Coffee to 'C',
        DrinkType.Tea to 'T',
        DrinkType.Chocolate to 'H',
    )

    override fun translateOrder(order: Order): String {
        val drinkTypeChar = drinkTypeMap[order.drinkType]

        return "$drinkTypeChar:${if (order.nbrOfSugar > 0) order.nbrOfSugar else ""}:${if (order.isStickAdded) 0 else ""}"
    }

    override fun translateMessage(message: String): String {
        return "M:$message"
    }
}