package coffeemachine

import org.assertj.core.api.Assertions
import kotlin.test.Test

class OrderStickTest {

    @Test
    fun `new order with sugar should have a stick`() {
        // given when
        val order = Order(DrinkType.COFFEE, 1)
        // then
        Assertions.assertThat(order.isStickAdded).isTrue()
    }

    @Test
    fun `new order without sugar should not have a stick`() {
        // given when
        val order = Order(DrinkType.COFFEE, 0)
        // then
        Assertions.assertThat(order.isStickAdded).isFalse()
    }
}