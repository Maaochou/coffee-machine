package coffeemachine

interface MakeDrink {
    fun processCommand(command: String): String
}