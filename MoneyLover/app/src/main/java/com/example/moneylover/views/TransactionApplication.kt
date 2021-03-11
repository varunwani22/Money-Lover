package com.example.moneylover.views

import android.app.Application
import com.example.moneylover.data.localtransaction.TransactionDatabase
import com.example.moneylover.repository.TransactionRepository

class TransactionApplication : Application() {

    private val transactionDao by lazy {
        val roomDatabase = TransactionDatabase.getDatabase(this)
        roomDatabase.getTransactionDao()
    }
    val repository by lazy {
        TransactionRepository(transactionDao)
    }
}