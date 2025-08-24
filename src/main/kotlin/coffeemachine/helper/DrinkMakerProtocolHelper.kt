package coffeemachine.helper

import coffeemachine.Order
import java.math.BigDecimal

class DrinkMakerProtocolHelper {

    companion object {
        fun formatOrder(order: Order): String =
            "${order.drinkType.value}:${if (order.numberOfSugarCubes > 0) order.numberOfSugarCubes else ""}:${if (order.isStickAdded) 0 else ""}"

        fun formatMessage(message: String): String = "M:$message"

        fun formatMissingAmount(missingAmount: BigDecimal): String =
            "You are missing the following amount : $missingAmount"
    }
}