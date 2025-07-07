package coffeemachine.service

import coffeemachine.domain.DrinkType
import coffeemachine.domain.InstructionType
import coffeemachine.domain.Order

class DrinkMakerProtocolCodecImpl : DrinkMakerProtocolCodec {

    val invalidInstructionsExceptionMessage = "Invalid instructions"

    val instructionToDrinkTypeMap = mapOf(
        InstructionType.T to DrinkType.T,
        InstructionType.C to DrinkType.C,
        InstructionType.H to DrinkType.H,
        InstructionType.O to DrinkType.O,
    )

    val instructionStringToInstructionTypeMap = mapOf(
        'M' to InstructionType.M,
        'T' to InstructionType.T,
        'C' to InstructionType.C,
        'H' to InstructionType.H,
        'O' to InstructionType.O,
    )

    override fun decode(instructions: String): Any {
        val instructionsArray: List<String?> = instructions.split(":")

        val instructionTypeString = instructionsArray.getOrNull(0) ?: ""
        val instructionTypeChar =
            instructionTypeString.getOrNull(0) ?: throw IllegalArgumentException(invalidInstructionsExceptionMessage)
        val isExtraHotDrink = instructionTypeString.getOrNull(1) == 'h'

        val instructionType: InstructionType =
            instructionStringToInstructionTypeMap[instructionTypeChar] ?: throw IllegalArgumentException(
                invalidInstructionsExceptionMessage
            )
        if (instructionType == InstructionType.M) {
            return instructionsArray.getOrNull(1) ?: ""
        }
        require((isExtraHotDrink && instructionTypeString.length == 2) || (!isExtraHotDrink && instructionTypeString.length == 1)) { invalidInstructionsExceptionMessage }


        val sugar: Int = instructionsArray.getOrNull(1)?.toIntOrNull() ?: 0
        val drinkType: DrinkType =
            instructionToDrinkTypeMap[instructionType] ?: throw IllegalArgumentException("Invalid drink type")
        return Order(drinkType, sugar, extraHot = isExtraHotDrink)
    }

    override fun encode(order: Order): String {
        return "${order.type}${if (order.extraHot) "h" else ""}:${order.sugar}:"
    }

    override fun encode(message: String): String {
        return "${InstructionType.M}:$message"
    }
}