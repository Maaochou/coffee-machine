package coffeemachine.service

import coffeemachine.Order

interface TranslateOrderToDrinkMakerProtocol {
    fun translate(order: Order): String
}