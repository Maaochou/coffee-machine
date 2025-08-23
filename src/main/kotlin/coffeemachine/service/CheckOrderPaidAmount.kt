package coffeemachine.service

import coffeemachine.Order

interface CheckOrderPaidAmount {
    fun isPaidAmountValid(order: Order): Boolean
}