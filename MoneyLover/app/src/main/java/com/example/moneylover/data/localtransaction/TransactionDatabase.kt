package com.example.moneylover.data.localtransaction

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 1)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDAO

    companion object {
        private var INSTANCE: TransactionDatabase? = null

        fun getDatabase(context: Context): TransactionDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "money_lover_db"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }
    }

}