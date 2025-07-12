package prep.mao.coffeemachine

interface TranslateDrinkMakerProtocol {
    fun translateOrder(order: Order): String
    fun translateMessage(message: String): String
}