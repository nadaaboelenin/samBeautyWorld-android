package com.app.sambeautyworld.ui.mobileAuth.sendOtp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.mobileAuth.enterOtp.OtpVerificationFragment
import kotlinx.android.synthetic.main.fragment_mobile_number_auth.*

/**
 * Created by ${Shubham} on 12/26/2018.
 */
class MobileAuthFragment : BaseFragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile_number_auth,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        btVerify.setOnClickListener {
            addFragment(OtpVerificationFragment(),true,R.id.container_main)
        }
    }
}