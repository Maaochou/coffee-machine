package coffeemachine

import prep.mao.coffeemachine.CommandType
import prep.mao.coffeemachine.DrinkType

class DrinkMaker : MakeDrink {


    val commandStringToCommandTypeMap = mapOf(
        "C" to CommandType.Coffee,
        "T" to CommandType.Tea,
        "H" to CommandType.Chocolate,
        "M" to CommandType.Message,
    )

    val commandTypeToDrinkTypeMap = mapOf(
        CommandType.Coffee to DrinkType.Coffee,
        CommandType.Tea to DrinkType.Tea,
        CommandType.Chocolate to DrinkType.Chocolate,
    )

    val drinkTypeToDrinkNameMap = mapOf(
        DrinkType.Coffee to "coffee",
        DrinkType.Tea to "tea",
        DrinkType.Chocolate to "chocolate",
    )

    override fun processCommand(command: String): String {
        val commandArray = command.split(":")
        val commandType = getCommandType(commandArray.get(0))

        if (commandType == CommandType.Message) {
            // TODO what if the message contains the separator ? should we substring instead ?
            return commandArray.getOrNull(1) ?: ""
        }

        val drinkName = getDrinkName(getDrinkType(commandType))
        val numberOfSugars =
            getNumberOfSugars(commandArray.getOrNull(1) ?: throw IllegalArgumentException("Unknown number of sugars"))
        val isStickAdded =
            isStickAdded(commandArray.getOrNull(2) ?: throw IllegalArgumentException("Unknown presence of stick"))

        require((isStickAdded && numberOfSugars > 0) || (!isStickAdded && numberOfSugars == 0)) { "Invalid stick and sugar combination" }

        return "Drink maker makes 1 $drinkName with ${if (numberOfSugars > 0) numberOfSugars else "no"} sugar${if (numberOfSugars > 1) "s" else ""}${if (isStickAdded) " and a stick" else ""}"
    }

    private fun getCommandType(commandTypeString: String): CommandType =
        commandStringToCommandTypeMap[commandTypeString] ?: throw IllegalArgumentException("Unknown command type")

    private fun getDrinkType(commandType: CommandType): DrinkType =
        commandTypeToDrinkTypeMap[commandType] ?: throw IllegalArgumentException("Unknown command type")

    private fun getDrinkName(drinkType: DrinkType): String =
        drinkTypeToDrinkNameMap[drinkType] ?: throw IllegalArgumentException("Unknown drink type")

    private fun getNumberOfSugars(commandSugarString: String): Int =
        if (commandSugarString.isEmpty()) 0 else commandSugarString.toInt()

    private fun isStickAdded(commandStickString: String): Boolean = commandStickString == "0"
}