package com.example.moneylover.views

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.example.moneylover.R
import com.example.moneylover.data.localtransaction.TransactionDatabase
import com.example.moneylover.data.localtransaction.TransactionEntity
import com.example.moneylover.repository.TransactionRepository
import com.example.moneylover.viewmodels.TransactionViewModel
import com.example.moneylover.viewmodels.TransactionViewModelFactory
import com.example.moneylover.views.bottomnavigation.TransactionFragment
import com.example.moneylover.views.recyclerviews.ItemClickOperation
import kotlinx.android.synthetic.main.activity_edit_transaction.*
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.util.*

class EditTransactionActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel
    private val transactionDao by lazy {
        val roomDatabase = TransactionDatabase.getDatabase(this)
        roomDatabase.getTransactionDao()
    }
    private val repository by lazy {
        TransactionRepository(transactionDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_transaction)


        val viewModelFactory = TransactionViewModelFactory(repository)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)



        etSelectEditableDate.setOnClickListener {
            val dp =
                DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        etSelectEditableDate.setText(dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString())
                    },
                    year,
                    month,
                    day
                )

            dp.show()
        }

        ivEditableSelectFromGallery.setOnClickListener {
            val builder = AlertDialog.Builder(
                this
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better. \n  \nGo Premium o enjoy unlimited awesome features!")
            builder.setPositiveButton("GO PREMIUM", null)
            val alert: AlertDialog = builder.create()
            alert.show()
        }

        ivEditableClickPicture.setOnClickListener {
            val builder = AlertDialog.Builder(
                this
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better.\n  \nGo Premium to enjoy unlimited awesome features! ")
            builder.setPositiveButton("GO PREMIUM", null)
            val alert: AlertDialog = builder.create()
            alert.show()
        }

        etSetEditableReminder.setOnClickListener {
            val time = etSetReminder.text.toString().toInt()
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, time)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 0)
            startActivity(intent)
        }


        val transactionEntity = TransactionEntity(0, "", "", "", "", "","")


        btnSaveEditedTransaction.setOnClickListener {

            val id = intent.getIntExtra("identity", 0)

            transactionEntity.id = id
            transactionEntity.date = etSelectEditableDate.text.toString()
            transactionEntity.category = etSelectEditableCategory.text.toString()
            transactionEntity.amount = etSelectEditableAmount.text.toString().toInt()
            transactionEntity.wallet = etSelectEditableWallet.text.toString()
            transactionEntity.note = etSelectEditableNote.text.toString()
            transactionEntity.with = etEditablePeopleWith.text.toString()
            viewModel.updateTransaction(transactionEntity)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}