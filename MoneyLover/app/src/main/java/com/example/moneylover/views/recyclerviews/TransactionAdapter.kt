package com.example.moneylover.views.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneylover.R
import com.example.moneylover.data.localtransaction.TransactionEntity

class TransactionAdapter(
    private val transactionList: List<TransactionEntity>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_for_transaction, parent, false)
        return TransactionViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transactionEntity: TransactionEntity = transactionList[position]
        holder.setData(transactionEntity)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

}