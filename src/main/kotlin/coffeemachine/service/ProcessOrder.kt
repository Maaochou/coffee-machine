package coffeemachine.service

import coffeemachine.domain.Order

interface ProcessOrder {
    fun processOrder(order: Order): Any
}