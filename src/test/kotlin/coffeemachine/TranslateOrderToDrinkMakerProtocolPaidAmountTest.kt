package coffeemachine

import coffeemachine.helper.DrinkMakerProtocolHelper
import coffeemachine.service.OrderToDrinkMakerProtocolTranslator
import coffeemachine.service.TranslateOrderToDrinkMakerProtocol
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal
import kotlin.test.Test

class TranslateOrderToDrinkMakerProtocolPaidAmountTest {

    val translateOrderDrinkMakerProtocol: TranslateOrderToDrinkMakerProtocol = OrderToDrinkMakerProtocolTranslator()

    @Test
    fun `order of tea with one sugar and 0 point 4 paid for tea that cost 0 point 4 should translate to 'T colon 1 colon 0'`() {
        // given
        val order = Order(DrinkType.TEA, 1, 0.4.toBigDecimal())
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "T:1:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of tea with one sugar and 1 paid for tea that cost 0 point 4 should translate to 'T colon 1 colon 0'`() {
        // given
        val order = Order(DrinkType.TEA, 1, BigDecimal.ONE)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "T:1:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of tea with one sugar and 0 paid for tea that cost 0 point 4 should translate to 'M colon You are missing the following amount colon 0 point 4'`() {
        // given
        val order = Order(DrinkType.TEA, 1, BigDecimal.ZERO)
        // when
        val messageInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expectedMissingAmount = 0.4.toBigDecimal()
        val expectedMessage =
            DrinkMakerProtocolHelper.formatMessage(DrinkMakerProtocolHelper.formatMissingAmount(expectedMissingAmount))
        // then
        assertThat(messageInDrinkMakerProtocol).contains(expectedMissingAmount.toString()).isEqualTo(expectedMessage)
    }

    @Test
    fun `order of tea with no sugar and 0 point 3 paid for tea that cost 0 point 4 should translate to 'M colon You are missing the following amount colon 0 point 1'`() {
        // given
        val order = Order(DrinkType.TEA, 1, 0.3.toBigDecimal())
        // when
        val messageInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expectedMissingAmount = 0.1.toBigDecimal()
        val expectedMessage =
            DrinkMakerProtocolHelper.formatMessage(DrinkMakerProtocolHelper.formatMissingAmount(expectedMissingAmount))
        // then
        assertThat(messageInDrinkMakerProtocol).contains(expectedMissingAmount.toString()).isEqualTo(expectedMessage)
    }

}