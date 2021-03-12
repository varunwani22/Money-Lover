package com.example.moneylover.views.bottomnavigation
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.moneylover.R
import com.example.moneylover.views.AboutActivity
import com.example.moneylover.views.SettingsActivity
import com.example.moneylover.views.SignInActivity
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment() {
    var auth: FirebaseAuth? = null


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

        return inflater.inflate(R.layout.fragment_account, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        val user = auth!!.currentUser
        var arr=user!!.email!!.split('@')
        tvName.text=arr[0]
        tvEmail.text=user!!.email.toString()
        btnSignOut.setOnClickListener {
            val builder=AlertDialog.Builder(requireContext())
            builder.setTitle("Wait a sec...")
            builder.setMessage("Siging out from the device?\n\nYour data on this device will be deleted, " +
                    "but you can always get them back once you sign in again.")
            builder.setPositiveButton("SIGN OUT"){dialog: DialogInterface?, which: Int ->
                auth!!.signOut()
                val intent = Intent(activity, SignInActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            builder.setNegativeButton("CANCEL"){dialogInterface, which ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()



        }
        llSettings.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llAboutUs.setOnClickListener{
            val intent = Intent (activity,AboutActivity::class.java)
            startActivity(intent)
        }
    }

}