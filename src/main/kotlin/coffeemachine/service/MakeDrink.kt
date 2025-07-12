package coffeemachine.service

interface MakeDrink {
    fun processCommand(command: String): String
}