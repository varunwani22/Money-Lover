package com.example.moneylover.views.recyclerviews

import com.example.moneylover.data.localtransaction.TransactionEntity

interface OnItemClickListener {
    fun onItemClick(transactionEntity: TransactionEntity)
}