package coffeemachine

import coffeemachine.domain.Drink
import coffeemachine.domain.DrinkType
import coffeemachine.service.DrinkMaker
import coffeemachine.service.DrinkMakerProtocolCodecImpl
import coffeemachine.service.MakeDrink
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class MakeDrinkWithAddedDrinksTest {
    val drinkMaker: MakeDrink = DrinkMaker(DrinkMakerProtocolCodecImpl())

    @Test
    fun drink_maker_makes_1_orange_juice() {
        // Given
        val instructions = "O::"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.O, 0, false, false)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_extra_hot_coffee_with_no_sugar_and_no_stick() {
        // Given
        val instructions = "Ch::"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.C, 0, false, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_extra_hot_chocolate_with_1_sugar_and_a_stick() {
        // Given
        val instructions = "Hh:1:0"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.H, 1, true, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_extra_hot_tea_with_2_sugar_and_a_stick() {
        // Given
        val instructions = "Th:2:0"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.T, 2, true, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_should_throw_an_error() {
        // Given
        val instructions = "Thll:2:0"
        // When // Then
        assertThrows(IllegalArgumentException::class.java) { drinkMaker.processInstructions(instructions) }
    }

}