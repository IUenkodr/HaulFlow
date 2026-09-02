package com.yocam.haulflow.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yocam.haulflow.data.local.TransactionDao
import kotlinx.coroutines.tasks.await

class SyncRepository(
    private val localDao: TransactionDao,
    private val firestore: FirebaseFirestore
) {
    suspend fun syncTransactions() {
        val unsynced = localDao.getUnsyncedTransactions()
        unsynced.forEach { tx ->
            firestore.collection("transactions")
                .document(tx.id)
                .set(tx)
                .await()
            localDao.markAsSynced(tx.id)
        }

        val snapshot = firestore.collection("transactions").get().await()
        snapshot.documents.forEach { doc ->
            val tx = doc.toObject(com.yocam.haulflow.data.local.TransactionEntity::class.java)
            tx?.let { localDao.insertTransaction(it) }
        }
    }
}
