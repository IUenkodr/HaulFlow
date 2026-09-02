package com.yocam.haulflow.domain.model

import java.math.BigDecimal
import java.util.Date

enum class TransactionType {
    INCOME, EXPENSE
}

enum class TransactionCategory {
    FREIGHT_PAYMENT, FUEL, MAINTENANCE, TOLLS, SALARY, MISC
}

data class Transaction(
    val id: String = java.util.UUID.randomUUID().toString(),
    val amount: BigDecimal,
    val type: TransactionType,
    val category: TransactionCategory,
    val note: String,
    val timestamp: Date = Date(),
    val isSynced: Boolean = false
)
