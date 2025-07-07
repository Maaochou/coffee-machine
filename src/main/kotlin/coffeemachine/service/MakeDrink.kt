package coffeemachine.service

import java.math.BigDecimal

interface MakeDrink {
    fun processInstructions(instructions: String, paidAmount: BigDecimal = BigDecimal.ZERO): Any
}