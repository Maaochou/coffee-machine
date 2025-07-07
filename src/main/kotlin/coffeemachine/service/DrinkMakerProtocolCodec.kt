package coffeemachine.service

import coffeemachine.domain.Order

interface DrinkMakerProtocolCodec {
    fun decode(instructions: String): Any
    fun encode(order: Order): String
    fun encode(message: String): String
}