package com.app.sambeautyworld.ui.mobileAuth.sendOtp

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.mobileAuth.enterOtp.OtpVerificationFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_mobile_number_auth.*


/**
 * Created by ${Shubham} on 12/26/2018.
 */
class MobileAuthFragment : BaseFragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_mobile_number_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        setUpData()
    }

    private fun setUpData() {
        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (etPhoneNumber.text.length >= 6) {
                    btVerify.background = ContextCompat.getDrawable(activity!!, R.drawable.drawable_button_white)
                    btVerify.setTextColor(ContextCompat.getColor(activity!!, android.R.color.black))

                    btVerify.isEnabled = true
                    btVerify.isClickable = true
                }
                //drawable_button_background
                else {
                    btVerify.background = ContextCompat.getDrawable(activity!!, R.drawable.drawable_button_background)
                    btVerify.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))
                    btVerify.isEnabled = false
                    btVerify.isClickable = false
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                Log.e("TextWatcherTest", "afterTextChanged:\t$s")
            }
        })
    }

    private fun clickListeners() {
        btVerify.setOnClickListener {
            val args: Bundle? = Bundle()
            if (!etPhoneNumber.text.toString().isEmpty()) {
                val otpVerificationFragment = OtpVerificationFragment()
                args?.putString(Constants.PHONE_NUMBER, "+" + tvCountryCode.selectedCountryCode.toString() + etPhoneNumber.text.toString())
                otpVerificationFragment.arguments = args
                addFragment(otpVerificationFragment, true, com.app.sambeautyworld.R.id.container_main)
            } else {
                etPhoneNumber.error = resources.getString(com.app.sambeautyworld.R.string.please_enter_a_valid_number)
            }
        }


        etPhoneNumber.setOnClickListener {
            val scrollview = view?.findViewById(com.app.sambeautyworld.R.id.nested) as NestedScrollView
            scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
        }
    }

}