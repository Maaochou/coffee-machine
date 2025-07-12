package coffeemachine

import prep.mao.coffeemachine.*
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class TakeOrderTest {

    val takeOrder: TakeOrder = OrderTaker(
        DrinkMakerProtocolTranslator(), DrinkMaker(),
        PriceChecker(
            mapOf(
                DrinkType.Coffee to 0.6.toBigDecimal(),
                DrinkType.Tea to 0.4.toBigDecimal(),
                DrinkType.Chocolate to 0.5.toBigDecimal(),
            )
        )
    )

    fun formatMissingAmontMessage(missingAmount: BigDecimal): String =
        "You are missing the following amount : $missingAmount euro"

    @Test
    fun `should make coffee with no sugar when paying more than the price 1_0`() {
        // Given
        val order = Order(DrinkType.Coffee, paidAmount = 1.toBigDecimal())
        //When
        val result = takeOrder.takeOrder(order)
        //Then
        val expected = "Drink maker makes 1 coffee with no sugar"
        assertEquals(expected, result)
    }

    @Test
    fun `should make tea with 1 sugar and a stick when paying exactly the asked price 0_4`() {
        // Given
        val order = Order(DrinkType.Tea, 1,true, paidAmount = 0.4.toBigDecimal())
        //When
        val result = takeOrder.takeOrder(order)
        //Then
        val expected = "Drink maker makes 1 tea with 1 sugar and a stick"
        assertEquals(expected, result)
    }

    @Test
    fun `should ask for 0_2 euros when paying 0_3 for chocolate`() {
        // Given
        val order = Order(DrinkType.Chocolate, paidAmount = 0.3.toBigDecimal())
        // When
        val result = takeOrder.takeOrder(order)
        // Then
        val expected = formatMissingAmontMessage(0.2.toBigDecimal())
        assertEquals(expected, result)
    }

    @Test
    fun `should ask for 0_6 euros when paying nothing for coffee`() {
        // Given
        val order = Order(DrinkType.Coffee, paidAmount = BigDecimal.ZERO)
        // When
        val result = takeOrder.takeOrder(order)
        // Then
        val expected = formatMissingAmontMessage(0.6.toBigDecimal())
        assertEquals(expected, result)
    }
}