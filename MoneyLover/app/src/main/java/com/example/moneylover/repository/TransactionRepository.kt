package com.example.moneylover.repository

import androidx.lifecycle.LiveData
import com.example.moneylover.data.localtransaction.TransactionDAO
import com.example.moneylover.data.localtransaction.TransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionRepository(private val transactionDAO: TransactionDAO) {

    fun addTransaction(transactionEntity: TransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDAO.addTransaction(transactionEntity)
        }
    }

    fun getTransaction(): LiveData<List<TransactionEntity>> {
        return transactionDAO.getTransaction()
    }

    fun updateTransaction(transactionEntity: TransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDAO.updateTransaction(transactionEntity)
        }
    }

    fun deleteTransaction(transactionEntity: TransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDAO.deleteTransaction(transactionEntity)
        }
    }
}