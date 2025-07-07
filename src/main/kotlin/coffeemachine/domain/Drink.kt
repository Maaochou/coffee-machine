package coffeemachine.domain

data class Drink(val type: DrinkType, val sugar: Int = 0, val stick: Boolean = false, val extraHot: Boolean = false)