package com.example.moneylover.views.recyclerviews

import com.example.moneylover.data.localtransaction.TransactionEntity

interface ItemClickOperation {
    fun onEditListener(transactionEntity: TransactionEntity)
    fun onDeleteListener(transactionEntity: TransactionEntity)
}