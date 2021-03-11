package com.example.moneylover.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneylover.data.localtransaction.TransactionEntity
import com.example.moneylover.repository.TransactionRepository

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    fun addTransaction(transactionEntity: TransactionEntity) {
        repository.addTransaction(transactionEntity)
    }

    fun getTransaction(): LiveData<List<TransactionEntity>> {
        return repository.getTransaction()
    }

    fun updateTransaction(transactionEntity: TransactionEntity) {
        repository.updateTransaction(transactionEntity)
    }

    fun deleteTransaction(transactionEntity: TransactionEntity) {
        repository.deleteTransaction(transactionEntity)
    }
}