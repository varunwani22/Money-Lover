package com.example.moneylover.data.localtransaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
class TransactionEntity(
    @ColumnInfo(name = "amount") var amount: Int,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "wallet") var wallet: String,

    @ColumnInfo(name = "note") var note: String?,
    @ColumnInfo(name = "with") var with: String?,

    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}