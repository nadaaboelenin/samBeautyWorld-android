package com.app.sambeautyworld.ui.splash

import Preferences
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.home.HomeActivity
import com.app.sambeautyworld.ui.mobileAuth.login.LoginFragment
import com.app.sambeautyworld.ui.mobileAuth.sendOtp.MobileAuthFragment
import com.app.sambeautyworld.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_splash.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * Created by ${Shubham} on 12/25/2018.
 */
class SplashFragment : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()

        try {
            val info = activity?.packageManager?.getPackageInfo(
                    "com.app.sambeautyworld",
                    PackageManager.GET_SIGNATURES)
            for (signature in info?.signatures!!) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }

    private fun setUpData() {
        Picasso.get().load(com.app.sambeautyworld.R.mipmap.splash).resize(1080, 1920).into(ivSplash)
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (Preferences.prefs!!.getBoolean(Constants.IS_LOGGED_IN, false)) {
                    startActivity(Intent(activity, HomeActivity::class.java))
                } else {
                    if (!Preferences.prefs!!.getBoolean(Constants.IS_NUMBER_VERIFIED, false)) {
                        addFragment(MobileAuthFragment(), false, com.app.sambeautyworld.R.id.container_main)
                    } else {
                        addFragment(LoginFragment(), false, com.app.sambeautyworld.R.id.container_main)
                    }
                }
            }
        }.start()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_splash, container, false)
    }


}