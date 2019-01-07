package com.app.sambeautyworld.ui.mobileAuth.enterOtp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.mobileAuth.otpVerified.OtpVerifiedFragment
import kotlinx.android.synthetic.main.opt_included.*

/**
 * Created by ${Shubham} on 12/26/2018.
 */
class OtpVerificationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_otp_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        initViews()
    }

    private fun initViews() {

        code_one_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText
                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_two_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_three_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_fourth_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    verifyOtp()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    private fun verifyOtp() {
        replaceFragment(OtpVerifiedFragment(),true,R.id.container_main)
    }

    private fun clickListeners() {

    }
}