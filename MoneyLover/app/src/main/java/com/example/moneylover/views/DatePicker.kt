package com.example.moneylover.views

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.moneylover.views.bottomnavigation.AddTransactionFragment

class DatePicker: DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addTransactionFragment = AddTransactionFragment()
    }


}