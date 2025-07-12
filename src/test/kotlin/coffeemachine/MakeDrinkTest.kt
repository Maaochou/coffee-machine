package coffeemachine

import coffeemachine.service.DrinkMaker
import coffeemachine.service.MakeDrink
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class MakeDrinkTest {

    val drinkMaker: MakeDrink = DrinkMaker()

    @Test
    fun `drink maker should make 1 tea with 1 sugar and a stick`() {
        // Given
        val command = "T:1:0"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 tea with 1 sugar and a stick"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should make 1 chocolate with no sugar and no stick`() {
        // Given
        val command = "H::"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 chocolate with no sugar"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should make 1 coffee with 2 sugars and a stick`() {
        // Given
        val command = "C:2:0"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 coffee with 2 sugars and a stick"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should forward the message content received`() {
        // Given
        val command = "M:message-content"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "message-content"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should throw an exception having no sugar and a stick`() {
        // Given
        val command = "H::0"
        // When // Then
        assertThrows(
            IllegalArgumentException::class.java,
            { drinkMaker.processCommand(command) },
            "Invalid stick and sugar combination"
        )
    }

    @Test
    fun `drink maker should throw an exception having sugar with no stick`() {
        // Given
        val command = "C:1:"
        // When // Then
        assertThrows(
            IllegalArgumentException::class.java,
            { drinkMaker.processCommand(command) },
            "Invalid stick and sugar combination"
        )
    }

    @Test
    fun `drink maker should make an extra hot tea with 1 sugar and a stick`() {
        // Given
        val command = "Th:1:0"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 tea with 1 sugar and a stick"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should make an extra hot chocolate with no sugar and no stick`() {
        // Given
        val command = "Hh::"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 chocolate with no sugar"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should make an extra hot coffee with 2 sugars and a stick`() {
        // Given
        val command = "Ch:2:0"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 coffee with 2 sugars and a stick"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should make one orange juice`() {
        // Given
        val command = "O::"
        // When
        val result = drinkMaker.processCommand(command)
        // Then
        val expected = "Drink maker makes 1 orange juice with no sugar"
        assertEquals(expected, result)
    }

    @Test
    fun `drink maker should throw an exception when making one extra hot orange juice`() {
        // Given
        val command = "Oh::"
        // When // Then
        assertThrows(
            IllegalArgumentException::class.java,
            { drinkMaker.processCommand(command) },
            "Invalid drink type and extra hot option combination"
        )
    }
}