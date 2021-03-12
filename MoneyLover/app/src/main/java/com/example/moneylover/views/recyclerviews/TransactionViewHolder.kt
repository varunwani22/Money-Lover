package com.example.moneylover.views.recyclerviews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moneylover.data.localtransaction.TransactionEntity
import kotlinx.android.synthetic.main.layout_for_transaction.view.*

class TransactionViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    fun setData(transactionEntity: TransactionEntity) {
        itemView.apply {
            tvTransactionDateToDisplay.text = transactionEntity.date
            tvTransactionCategory.text = transactionEntity.category
            tvTransactionAmount.text = transactionEntity.amount.toString()
            Glide.with(ivCategoryImage.context).load(transactionEntity.image).into(ivCategoryImage)


            rlUpdateTransaction.setOnClickListener {
                onItemClickListener.onItemClick(transactionEntity)
            }
        }
    }
}