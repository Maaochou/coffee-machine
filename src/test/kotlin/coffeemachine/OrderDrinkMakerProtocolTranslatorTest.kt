package coffeemachine

import coffeemachine.service.OrderToDrinkMakerProtocolTranslator
import coffeemachine.service.TranslateOrderToDrinkMakerProtocol
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class OrderDrinkMakerProtocolTranslatorTest {

    val translateOrderDrinkMakerProtocol: TranslateOrderToDrinkMakerProtocol = OrderToDrinkMakerProtocolTranslator()

    @Test
    fun `order of tea with one sugar and no stick should translate to 'T colon 1 colon 0'`() {
        // given
        val order = Order(DrinkType.TEA, 1)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "T:1:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of chocolate with no sugar and no stick should translate to 'H colon colon'`() {
        // given
        val order = Order(DrinkType.CHOCOLATE, 0)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "H::"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of coffee with two sugar and a stick should translate to 'C colon 2 colon 0'`() {
        // given
        val order = Order(DrinkType.COFFEE, 2)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "C:2:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `string message 'message-content' should translate to 'M colon message-content'`() {
        // given
        val message = "message-content"
        // when
        val messageInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(message);
        val expected = "M:message-content"
        // then
        assertThat(messageInDrinkMakerProtocol).isEqualTo(expected)
    }

}