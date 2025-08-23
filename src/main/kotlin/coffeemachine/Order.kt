package coffeemachine

data class Order(val drinkType: DrinkType, val numberOfSugarCubes: Int) {
    val isStickAdded: Boolean = numberOfSugarCubes > 0
}