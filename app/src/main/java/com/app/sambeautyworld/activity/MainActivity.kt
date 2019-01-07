package com.app.sambeautyworld.activity

import android.os.Bundle
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseActivity
import com.app.sambeautyworld.ui.splash.SplashFragment

class MainActivity : BaseActivity() {
    override fun getID(): Int {
        return R.layout.activity_main
    }

    override fun iniView(savedInstanceState: Bundle?) {
        initViews()
    }


    private fun initViews() {
        addFragment(SplashFragment(),true, R.id.container_main)
    }


}
