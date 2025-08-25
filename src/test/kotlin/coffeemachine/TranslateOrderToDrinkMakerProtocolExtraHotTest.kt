package coffeemachine

import coffeemachine.service.OrderToDrinkMakerProtocolTranslator
import coffeemachine.service.TranslateOrderToDrinkMakerProtocol
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.math.BigDecimal
import kotlin.test.Test

class TranslateOrderToDrinkMakerProtocolExtraHotTest {

    val translateOrderDrinkMakerProtocol: TranslateOrderToDrinkMakerProtocol = OrderToDrinkMakerProtocolTranslator()

    @Test
    fun `order of extra hot tea with one sugar and no stick should translate to 'Th colon 1 colon 0'`() {
        // given
        val order = Order(DrinkType.TEA, 1, BigDecimal.ONE, isExtraHot = true)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "Th:1:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of extra hot chocolate with no sugar and no stick should translate to 'Hh colon colon'`() {
        // given
        val order = Order(DrinkType.CHOCOLATE, 0, BigDecimal.ONE, isExtraHot = true)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "Hh::"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of extra hot coffee with two sugar and a stick should translate to 'Ch colon 2 colon 0'`() {
        // given
        val order = Order(DrinkType.COFFEE, 2, BigDecimal.ONE, isExtraHot = true)
        // when
        val orderInDrinkMakerProtocol = translateOrderDrinkMakerProtocol.translate(order);
        val expected = "Ch:2:0"
        // then
        assertThat(orderInDrinkMakerProtocol).isEqualTo(expected)
    }

    @Test
    fun `order of extra hot orange juice should throw an exception with the following message 'The drink type ORANGE_JUICE can't be sold extra hot'`() {
        // when then
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Order(
                DrinkType.ORANGE_JUICE, paidAmount = BigDecimal.ONE, isExtraHot = true
            )
        }.withMessage("The drink type ORANGE_JUICE can't be sold extra hot")
    }
}