package com.example.appprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecentTransactionsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_transactions)

        recyclerView = findViewById(R.id.transactionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        db = AppDatabase.getDatabase(this)

        loadTransactions()
    }

    private fun loadTransactions() {
        lifecycleScope.launch {
            val transactions = withContext(Dispatchers.IO) {
                db.transactionDao().getAllTransactions()
            }
            adapter = TransactionAdapter(transactions)
            recyclerView.adapter = adapter
        }
    }
}
