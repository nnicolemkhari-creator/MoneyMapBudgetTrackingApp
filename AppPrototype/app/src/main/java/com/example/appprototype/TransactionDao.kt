package com.example.appprototype

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    suspend fun getAllTransactions(): List<Transaction>

    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()
}