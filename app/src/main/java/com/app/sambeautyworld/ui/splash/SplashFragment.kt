package com.app.sambeautyworld.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_splash.*
import android.os.CountDownTimer
import com.app.sambeautyworld.ui.mobileAuth.sendOtp.MobileAuthFragment
/**
 * Created by ${Shubham} on 12/25/2018.
 */
class SplashFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        Picasso.get().load(R.mipmap.splash).resize(1080 , 1920).into(ivSplash)
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
              addFragment(MobileAuthFragment(),false,R.id.container_main)
            }
        }.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }
}