package coffeemachine.service

import coffeemachine.domain.Drink
import coffeemachine.domain.Order
import java.math.BigDecimal

class DrinkMaker(
    val drinkMakerProtocolCodec: DrinkMakerProtocolCodec
) : MakeDrink {

    override fun processInstructions(instructions: String, paidAmount: BigDecimal): Any {
        val decodedObject = drinkMakerProtocolCodec.decode(instructions)
        if (decodedObject is String) return decodedObject

        decodedObject is Order || throw IllegalArgumentException("Invalid decoded Object")

        return Drink(decodedObject.type, decodedObject.sugar, decodedObject.sugar > 0, decodedObject.extraHot)
    }

}