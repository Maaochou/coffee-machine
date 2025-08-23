package coffeemachine

import coffeemachine.service.CheckOrderPaidAmount
import coffeemachine.service.OrderPaidAmountChecker
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal
import kotlin.test.Test

class CheckOrderPaidAmountTest {

    val orderPaidAmountChecker: CheckOrderPaidAmount = OrderPaidAmountChecker()

    @Test
    fun `order with paid amount 0 point 0 of coffee that cost 0 point 6 should fail paid amount validation`() {
        // given
        val order = Order(DrinkType.COFFEE, paidAmount = BigDecimal.ZERO)
        // when
        val isPaidAmountValid = orderPaidAmountChecker.isPaidAmountValid(order)
        // then
        assertThat(isPaidAmountValid).withFailMessage("Paid amount ${order.paidAmount} should be more than drink type price ${order.drinkType.price}")
            .isFalse()
    }

    @Test
    fun `order with paid amount 0 point 4 of coffee that cost 0 point 6 should fail paid amount validation`() {
        // given
        val order = Order(DrinkType.COFFEE, paidAmount = 0.4.toBigDecimal())
        // when
        val isPaidAmountValid = orderPaidAmountChecker.isPaidAmountValid(order)
        // then
        assertThat(isPaidAmountValid).withFailMessage("Paid amount ${order.paidAmount} should be more than drink type price ${order.drinkType.price}")
            .isFalse()
    }

    @Test
    fun `order with paid amount 0 point 6 of coffee that cost 0 point 6 should pass paid amount validation`() {
        // given
        val order = Order(DrinkType.COFFEE, paidAmount = 0.6.toBigDecimal())
        // when
        val isPaidAmountValid = orderPaidAmountChecker.isPaidAmountValid(order)
        // then
        assertThat(isPaidAmountValid).withFailMessage("Paid amount ${order.paidAmount} should be more than drink type price ${order.drinkType.price}")
            .isTrue()
    }

    @Test
    fun `order with paid amount 1 of coffee that cost 0 point 6 should pass paid amount validation`() {
        // given
        val order = Order(DrinkType.COFFEE, paidAmount = BigDecimal.ONE)
        // when
        val isPaidAmountValid = orderPaidAmountChecker.isPaidAmountValid(order)
        // then
        assertThat(isPaidAmountValid).withFailMessage("Paid amount ${order.paidAmount} should be more than drink type price ${order.drinkType.price}")
            .isTrue()
    }
}