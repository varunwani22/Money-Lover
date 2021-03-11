package com.example.moneylover.views.bottomnavigation

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.moneylover.R
import com.example.moneylover.data.localtransaction.TransactionDatabase
import com.example.moneylover.data.localtransaction.TransactionEntity
import com.example.moneylover.repository.TransactionRepository
import com.example.moneylover.viewmodels.TransactionViewModel
import com.example.moneylover.viewmodels.TransactionViewModelFactory
import com.example.moneylover.views.MainActivity
import com.example.moneylover.views.TransactionApplication
import com.example.moneylover.views.recyclerviews.OnItemClickListener
import com.example.moneylover.views.recyclerviews.TransactionAdapter
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.util.*


class AddTransactionFragment : Fragment() {

    private val transactionDao by lazy {
        val roomDatabase = TransactionDatabase.getDatabase(context!!)
        roomDatabase.getTransactionDao()
    }
    val repository by lazy {
        TransactionRepository(transactionDao)
    }

    private lateinit var viewModel: TransactionViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_transaction, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)



        etSelectDate.setOnClickListener {
            val dp = activity?.let {
                DatePickerDialog(
                    it,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        etSelectDate.setText(dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString())
                    },
                    year,
                    month,
                    day
                )
            }
            dp?.show()
        }

        ivSelectFromGallery.setOnClickListener {
            val builder = AlertDialog.Builder(
                context!!
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better. \n  \nGo Premium o enjoy unlimited awesome features!")
            val alert: AlertDialog = builder.create()
            alert.show()
        }
        ivClickPicture.setOnClickListener {
            val builder = AlertDialog.Builder(
                context!!
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better.\n  \nGo Premium to enjoy unlimited awesome features! ")
            val alert: AlertDialog = builder.create()
            alert.show()
        }

        etSetReminder.setOnClickListener {
            val time = etSetReminder.text.toString().toInt()
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, time)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 0)
            startActivity(intent)
        }

        val viewModelFactory = TransactionViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TransactionViewModel::class.java)

        btnSaveTransaction.setOnClickListener {
            val date = etSelectDate.text.toString()
            val category = etSelectCategory.text.toString()
            val amount = etSelectAmount.text.toString().toInt()

            val transactionEntity = TransactionEntity(amount, category, date, "cash")

            viewModel.addTransaction(transactionEntity)

            startActivity(Intent(activity, MainActivity::class.java))
            requireActivity().finish()
        }

    }


}