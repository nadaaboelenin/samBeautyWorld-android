package com.app.sambeautyworld.ui.mobileAuth.otpVerified

import Preferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.enableLocation.EnableLocationServices
import com.app.sambeautyworld.ui.mobileAuth.login.LoginFragment
import com.app.sambeautyworld.utils.Constants

/**
 * Created by ${Shubham} on 12/26/2018.
 */
class OtpVerifiedFragment: BaseFragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_opt_verified,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToNext()
    }

    private fun moveToNext() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                if (Preferences.prefs!!.getBoolean(Constants.IS_LOGGED_IN, false)) {
                    replaceFragment(EnableLocationServices(), true, R.id.container_main)
                } else {
                    addFragment(LoginFragment(), false, R.id.container_main)
                }
            }
        }.start()
    }

}