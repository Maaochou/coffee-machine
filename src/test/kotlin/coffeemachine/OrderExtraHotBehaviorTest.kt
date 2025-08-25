package coffeemachine

import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.Assertions.assertThatNoException
import java.math.BigDecimal
import kotlin.test.Test

class OrderExtraHotBehaviorTest {

    @Test
    fun `order of extra hot orange juice should throw an exception with the following message 'The drink type ORANGE_JUICE can't be sold extra hot'`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Order(
                DrinkType.ORANGE_JUICE, paidAmount = BigDecimal.ONE, isExtraHot = true
            )
        }.withMessage("The drink type ORANGE_JUICE can't be sold extra hot")
    }

    @Test
    fun `order of extra hot coffee should not throw an exception`() {
        assertThatNoException().isThrownBy {
            Order(
                DrinkType.COFFEE, paidAmount = BigDecimal.ONE, isExtraHot = true
            )
        }
    }
}