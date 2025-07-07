package coffeemachine

import coffeemachine.domain.Drink
import coffeemachine.domain.DrinkType
import coffeemachine.domain.Order
import coffeemachine.service.DrinkMaker
import coffeemachine.service.DrinkMakerProtocolCodecImpl
import coffeemachine.service.OrderProcessor
import coffeemachine.service.ProcessOrder
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class ProcessOrderWithPricesTest {
    val orderProcessor: ProcessOrder = OrderProcessor(
        DrinkMaker(),
        DrinkMakerProtocolCodecImpl(), mapOf(
            DrinkType.T to 0.4.toBigDecimal(),
            DrinkType.C to 0.6.toBigDecimal(),
            DrinkType.H to 0.5.toBigDecimal(),
        )
    )

    @Test
    fun drink_maker_makes_1_tea_with_1_sugar_and_a_stick_with_exact_price_paid() {
        // Given
        val order = Order(DrinkType.T, 1, paidAmount = 0.4.toBigDecimal())
        // When
        val drink = orderProcessor.processOrder(order)
        // Then
        val expectedDrink = Drink(DrinkType.T, 1, true)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_makes_1_chocolate_with_no_sugar_and_no_stick_with_more_than_the_exact_price_paid() {
        // Given
        val instructions = "H::"
        val order = Order(DrinkType.H, 0, paidAmount = 1.0.toBigDecimal())
        // When
        val drink = orderProcessor.processOrder(order)
        // Then
        val expectedDrink = Drink(DrinkType.H, 0, false)
        assertEquals(expectedDrink, drink)
    }

    @Test
    fun drink_maker_return_message_telling_us_that_0_point_2_is_missing_for_coffee() {
        // Given
        val order = Order(DrinkType.C, 2, paidAmount = 0.4.toBigDecimal())
        // When
        val message = orderProcessor.processOrder(order)
        // Then
        val expectedMessage = "Not enough money for drink, you're missing 0.2 euros"
        assertEquals(expectedMessage, message)
    }

    @Test
    fun drink_maker_return_message_telling_us_that_0_point_4_is_missing_for_tea() {
        // Given
        val order = Order(DrinkType.T, 2, paidAmount = BigDecimal.ZERO)
        // When
        val message = orderProcessor.processOrder(order)
        // Then
        val expectedMessage = "Not enough money for drink, you're missing 0.4 euros"
        assertEquals(expectedMessage, message)

    }
}