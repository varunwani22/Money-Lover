package com.example.moneylover.views.login_page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.moneylover.R
import com.example.moneylover.views.RegisterActivity
import com.example.moneylover.views.SignInActivity
import kotlinx.android.synthetic.main.fragment_login2.*
import kotlinx.android.synthetic.main.fragment_login3.*


class LoginFragment3 : Fragment() {
    private var mTvData: TextView? = null
    private var mParam1: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login3, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

//        mTvData = view.findViewById(R.id.tvData)
//        mTvData.setText(mParam1)
        btnMoneyLoverLogin3.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))
        }
        btnLoginAlreadyExists3.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onDetach() {
        super.onDetach()

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private val TAG = LoginFragment3::class.java.simpleName
        fun newInstance(param1: String?): LoginFragment3{
            val fragment = LoginFragment3()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}