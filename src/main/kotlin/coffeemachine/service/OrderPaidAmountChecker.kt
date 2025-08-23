package coffeemachine.service

import coffeemachine.Order

class OrderPaidAmountChecker : CheckOrderPaidAmount {

    override fun isPaidAmountValid(order: Order): Boolean = order.paidAmount >= order.drinkType.price

}