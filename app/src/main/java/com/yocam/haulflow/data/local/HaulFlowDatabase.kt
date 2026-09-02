package com.yocam.haulflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yocam.haulflow.data.local.TransactionDao
import com.yocam.haulflow.data.local.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class HaulFlowDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
