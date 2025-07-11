package prep.mao.coffeemachine

data class Order(val drinkType: DrinkType, val nbrOfSugar: Int = 0, val isStickAdded: Boolean = false)