package com.example.moneylover.views.bottomnavigation

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moneylover.R
import com.example.moneylover.data.localtransaction.TransactionDatabase
import com.example.moneylover.data.localtransaction.TransactionEntity
import com.example.moneylover.databinding.DialogCustomListBinding
import com.example.moneylover.databinding.FragmentAddTransactionBinding
import com.example.moneylover.repository.TransactionRepository
import com.example.moneylover.viewmodels.TransactionViewModel
import com.example.moneylover.viewmodels.TransactionViewModelFactory
import com.example.moneylover.views.Constants
import com.example.moneylover.views.MainActivity
import com.example.moneylover.views.TransactionApplication
import com.example.moneylover.views.recyclerviews.CustomListItemAdapter
import com.example.moneylover.views.recyclerviews.OnCategoryClickListener
import com.example.moneylover.views.recyclerviews.OnItemClickListener
import com.example.moneylover.views.recyclerviews.TransactionAdapter
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class AddTransactionFragment : Fragment(), OnCategoryClickListener {


    private val transactionDao by lazy {
        val roomDatabase = TransactionDatabase.getDatabase(requireContext())
        roomDatabase.getTransactionDao()
    }
    val repository by lazy {
        TransactionRepository(transactionDao)
    }

    private lateinit var viewModel: TransactionViewModel
    private lateinit var mCustomListDialog: Dialog

    //    private lateinit var mBinding: FragmentAddTransactionBinding
    private var mImagePath: String = ""

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
        etSelectCategory.setOnClickListener {
            customItemsListDialog(
                resources.getString(R.string.title_select_category),
                Constants.categories(),
                Constants.imageCategory(),
                Constants.CATEGORY
            )
        }
        etSelectWallet.setOnClickListener {
            customItemsListDialog(
                resources.getString(R.string.title_select_wallet),
                Constants.wallets(),
                Constants.walletsImage(),
                Constants.WALLET
            )

        }

        ivSelectFromGallery.setOnClickListener {
            val builder = AlertDialog.Builder(
                requireContext()
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better. \n  \nGo Premium o enjoy unlimited awesome features!")
            builder.setPositiveButton("GO PREMIUM", null)
            val alert: AlertDialog = builder.create()
            alert.show()
        }
        ivClickPicture.setOnClickListener {
            val builder = AlertDialog.Builder(
                requireContext()
            )
            builder.setTitle("Wait a sec...")
            builder.setMessage("Free is great but Premium s better.\n  \nGo Premium to enjoy unlimited awesome features! ")
            builder.setPositiveButton("GO PREMIUM", null)
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
            val wallet = etSelectWallet.text.toString()
            val note = etSelectNote.text.toString()
            val with = etPeopleWith.text.toString()
            val image = ivCategoryEt.drawable.toBitmap()
            mImagePath = saveImageToInternalStorage(image)

            val transactionEntity = TransactionEntity(amount, category, date, wallet, note, with, mImagePath)


            viewModel.addTransaction(transactionEntity)

            startActivity(Intent(activity, MainActivity::class.java))
            requireActivity().finish()
        }

    }

    private fun customItemsListDialog(
        title: String,
        itemsList: List<String>,
        imageList: List<Int>,
        selection: String
    ) {

        mCustomListDialog = Dialog(requireContext())
        val binding: DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)
        mCustomListDialog.setContentView(binding.root)

        binding.tvTitle.text = title
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        val adapter =
            CustomListItemAdapter(requireActivity(), itemsList, imageList, selection, this)

        binding.rvList.adapter = adapter

        mCustomListDialog.show()

    }

    override fun selectedListItem(item: String, image: Int, selection: String) {
        when (selection) {

            Constants.CATEGORY -> {
                mCustomListDialog.dismiss()
                etSelectCategory.setText(item)
                Glide.with(ivCategoryEt.context).load(image).into(ivCategoryEt)
            }
            Constants.WALLET -> {
                mCustomListDialog.dismiss()
                etSelectWallet.setText(item)
                Glide.with(ivWalletEt.context).load(image).into(ivWalletEt)
            }

        }
    }

//        fun selectedListItem(item: String, image: Int, selection: String) {
//
//            when (selection) {
//
//                Constants.CATEGORY -> {
//                    mCustomListDialog.dismiss()
//                    mBinding.etSelectCategory.setText(item)
//                }
//
//            }
//        }
private fun saveImageToInternalStorage(bitmap: Bitmap): String {


    val wrapper = ContextWrapper(context)


    var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)

    // Mention a file name to save the image
    file = File(file, "${UUID.randomUUID()}.jpg")

    try {
        // Get the file output stream
        val stream: OutputStream = FileOutputStream(file)

        // Compress bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        // Flush the stream
        stream.flush()

        // Close stream
        stream.close()
    } catch (e: IOException) { // Catch the exception
        e.printStackTrace()
    }

    // Return the saved image absolute path
    return file.absolutePath
}
    companion object{
        val IMAGE_DIRECTORY="CategoryImage"
    }


}