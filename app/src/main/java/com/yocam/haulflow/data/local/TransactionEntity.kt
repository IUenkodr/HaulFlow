package com.yocam.haulflow.data.local

import androidx.room.*
import com.yocam.haulflow.domain.model.TransactionCategory
import com.yocam.haulflow.domain.model.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val amount: Double,
    val type: TransactionType,
    val category: TransactionCategory,
    val note: String,
    val timestamp: Long,
    val isSynced: Boolean
)
