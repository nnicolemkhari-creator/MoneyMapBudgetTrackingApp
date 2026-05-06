package com.example.appprototype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
    private val transactions: List<Transaction>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
        val textAmount: TextView = itemView.findViewById(R.id.textAmount)
        val textCategory: TextView = itemView.findViewById(R.id.textCategory)
        val imageIcon: ImageView = itemView.findViewById(R.id.imageCategoryIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        val t = transactions[position]

        holder.textTitle.text = t.title
        holder.textDate.text = t.date
        holder.textCategory.text = t.category

        // ✅ FIXED type check (case-insensitive)
        holder.textAmount.text =
            if (t.type.equals("Income", true)) {
                "+ R %.2f".format(t.amount)
            } else {
                "- R %.2f".format(t.amount)
            }

        holder.imageIcon.setImageResource(getCategoryIcon(t.category))
    }

    override fun getItemCount(): Int = transactions.size

    // ✅ FIXED icon mapping
    private fun getCategoryIcon(category: String): Int {
        return when (category) {
            "Food" -> R.drawable.bg_icon_food
            "Transport" -> R.drawable.bg_icon_transport
            "Bills" -> R.drawable.bg_icon_bills
            "Shopping" -> R.drawable.bg_icon_shopping
            "Entertainment" -> R.drawable.bg_icon_entertainment
            "Health" -> R.drawable.ic_health
            else -> R.drawable.category_item_bg
        }
    }
}