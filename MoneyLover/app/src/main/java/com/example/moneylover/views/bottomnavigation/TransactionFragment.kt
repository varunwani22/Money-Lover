package com.example.moneylover.views.bottomnavigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moneylover.R
import com.example.moneylover.data.localtransaction.TransactionDatabase
import com.example.moneylover.data.localtransaction.TransactionEntity
import com.example.moneylover.repository.TransactionRepository
import com.example.moneylover.viewmodels.TransactionViewModel
import com.example.moneylover.viewmodels.TransactionViewModelFactory
import com.example.moneylover.views.ShowTransactionDetails
import com.example.moneylover.views.WebViewActivity
import com.example.moneylover.views.recyclerviews.OnItemClickListener
import com.example.moneylover.views.recyclerviews.TransactionAdapter
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: TransactionViewModel
    lateinit var transactionAdapter: TransactionAdapter
    private val transactionDao by lazy {
        val roomDatabase = TransactionDatabase.getDatabase(requireContext())
        roomDatabase.getTransactionDao()
    }
    val repository by lazy {
        TransactionRepository(transactionDao)
    }
    var transactionList = mutableListOf<TransactionEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAds()
        setRecyclerData()

        val viewModelFactory = TransactionViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TransactionViewModel::class.java)

        viewModel.getTransaction().observe(viewLifecycleOwner, Observer {
            transactionList.clear()
            transactionList.addAll(it)
            transactionAdapter.notifyDataSetChanged()
        })


    }

    private fun setAds() {
        Glide.with(ivAdvertise).load("https://media1.giphy.com/media/l1KuejTOGd5aVGN20/giphy.gif")
            .into(ivAdvertise)

        ivAdvertise.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("one", "https://www.makemytrip.com/")
            startActivity(intent)
        })
    }

    private fun setRecyclerData() {
        transactionAdapter = TransactionAdapter(transactionList, this)
        transactionRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionRecyclerView.adapter = transactionAdapter
    }

    override fun onItemClick(transactionEntity: TransactionEntity) {
        val intent = Intent(context, ShowTransactionDetails::class.java)
        intent.putExtra("id", transactionEntity.id)
        intent.putExtra("amount", transactionEntity.amount)
        intent.putExtra("cat", transactionEntity.category)
        intent.putExtra("note", transactionEntity.note)
        intent.putExtra("date", transactionEntity.date)
        intent.putExtra("wallet", transactionEntity.wallet)
        intent.putExtra("with", transactionEntity.with)
        startActivity(intent)
    }

}