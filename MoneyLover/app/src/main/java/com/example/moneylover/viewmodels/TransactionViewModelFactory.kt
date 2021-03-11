package com.example.moneylover.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneylover.repository.TransactionRepository

class TransactionViewModelFactory(private val repository: TransactionRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(repository) as T
    }

}