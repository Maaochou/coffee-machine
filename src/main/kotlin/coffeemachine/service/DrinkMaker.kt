package coffeemachine.service

import prep.mao.coffeemachine.CommandType
import prep.mao.coffeemachine.DrinkType

class DrinkMaker : MakeDrink {

    val drinkTypeCharToDrinkTypeMao = mapOf(
        'C' to DrinkType.Coffee,
        'T' to DrinkType.Tea,
        'H' to DrinkType.Chocolate,
        'O' to DrinkType.OrangeJuice,
    )

    val drinkTypeToDrinkNameMap = mapOf(
        DrinkType.Coffee to "coffee",
        DrinkType.Tea to "tea",
        DrinkType.Chocolate to "chocolate",
        DrinkType.OrangeJuice to "orange juice",
    )

    override fun processCommand(command: String): String {
        val commandArray = command.split(":")
        val commandFirstPart: String = commandArray[0]
        val commandType = getCommandType(commandFirstPart.get(0))

        if (commandType == CommandType.Message) {
            // TODO what if the message contains the separator ? should we substring instead ?
            return commandArray.getOrNull(1) ?: ""
        }

        val drinkType = getDrinkType(commandFirstPart[0])
        val isExtraHot = commandFirstPart.getOrNull(2) == 'h' ?: false

        require((DrinkType.OrangeJuice != drinkType) || (DrinkType.OrangeJuice == drinkType && !isExtraHot)) { "Invalid drink type and extra hot option combination" }

        val drinkName = getDrinkName(drinkType)
        val numberOfSugars =
            getNumberOfSugars(commandArray.getOrNull(1) ?: throw IllegalArgumentException("Unknown number of sugars"))
        val isStickAdded =
            isStickAdded(commandArray.getOrNull(2) ?: throw IllegalArgumentException("Unknown presence of stick"))

        require((isStickAdded && numberOfSugars > 0) || (!isStickAdded && numberOfSugars == 0)) { "Invalid stick and sugar combination" }

        return computeProcessedCommandString(drinkName, isExtraHot, numberOfSugars, isStickAdded)
    }

    fun computeProcessedCommandString(
        drinkName: String,
        isExtraHot: Boolean,
        numberOfSugars: Int,
        isStickAdded: Boolean
    ): String =
        "Drink maker makes 1${if (isExtraHot) " extra hot" else ""} $drinkName with ${if (numberOfSugars > 0) numberOfSugars else "no"} sugar${if (numberOfSugars > 1) "s" else ""}${if (isStickAdded) " and a stick" else ""}"

    private fun getCommandType(commandTypeString: Char): CommandType = when (commandTypeString) {
        'C', 'T', 'H', 'O' -> CommandType.Drink
        'M' -> CommandType.Message
        else -> throw IllegalArgumentException("Unknown command type")
    }


    private fun getDrinkType(drinkTypeChar: Char): DrinkType =
        drinkTypeCharToDrinkTypeMao[drinkTypeChar] ?: throw IllegalArgumentException("Unknown drink type")

    private fun getDrinkName(drinkType: DrinkType): String =
        drinkTypeToDrinkNameMap[drinkType] ?: throw IllegalArgumentException("Unknown drink type")

    private fun getNumberOfSugars(commandSugarString: String): Int =
        if (commandSugarString.isEmpty()) 0 else commandSugarString.toInt()

    private fun isStickAdded(commandStickString: String): Boolean = commandStickString == "0"
}