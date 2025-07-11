package coffeemachine

import prep.mao.coffeemachine.DrinkMakerProtocolTranslator
import prep.mao.coffeemachine.DrinkType
import prep.mao.coffeemachine.Order
import prep.mao.coffeemachine.TranslateDrinkMakerProtocol
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslateOrderTest {

    val translateOrder: TranslateDrinkMakerProtocol = DrinkMakerProtocolTranslator()

    @Test
    fun translated_order_should_be_T_1_0() {
        // Given
        val order = Order(DrinkType.Tea, 1, true)
        // When
        val result = translateOrder.translateOrder(order)
        // Then
        val expected = "T:1:0"
        assertEquals(expected, result)
    }

    @Test
    fun translated_order_should_be_H() {
        // Given
        val order = Order(DrinkType.Chocolate)
        // When
        val result = translateOrder.translateOrder(order)
        // Then
        val expected = "H::"
        assertEquals(expected, result)
    }

    @Test
    fun translated_order_should_be_C_2_0() {
        // Given
        val order = Order(DrinkType.Coffee, 2, true)
        // When
        val result = translateOrder.translateOrder(order)
        // Then
        val expected = "C:2:0"
        assertEquals(expected, result)
    }

    @Test
    fun translated_message_should_be_M_message_content() {
        // Given
        val message = "message-content"
        // When
        val result = translateOrder.translateMessage(message)
        // Then
        val expected = "M:message-content"
        assertEquals(expected, result)
    }
}