package coffeemachine

import coffeemachine.domain.Drink
import coffeemachine.domain.DrinkType
import coffeemachine.service.DrinkMaker
import coffeemachine.service.DrinkMakerProtocolCodecImpl
import coffeemachine.service.MakeDrink
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class MakeDrinkTest {
    val drinkMaker: MakeDrink = DrinkMaker(DrinkMakerProtocolCodecImpl())

    @Test
    fun drink_maker_makes_1_tea_with_1_sugar_and_a_stick() {
        // Given
        val instructions = "T:1:0"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.T, 1, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_chocolate_with_no_sugar_and_no_stick() {
        // Given
        val instructions = "H::"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.H, 0, false)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_coffee_with_2_sugars_and_a_stick() {
        // Given
        val instructions = "C:2:0"
        // When
        val drink = drinkMaker.processInstructions(instructions)
        // Then
        val expectedDrink = Drink(DrinkType.C, 2, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_forwards_the_message_received_from_the_user() {
        // Given
        val instructions = "M:message-content"
        // When
        val message = drinkMaker.processInstructions(instructions)
        // Then
        val expectedMessage = "message-content"
        assertEquals(expectedMessage, message)
    }

    @Test
    fun drink_maker_sends_error_for_invalid_message() {
        // Given
        val instructions = "invalidType:invalid-message-content"
        // When // Then
        assertThrows(IllegalArgumentException::class.java) { drinkMaker.processInstructions(instructions) }
    }
}