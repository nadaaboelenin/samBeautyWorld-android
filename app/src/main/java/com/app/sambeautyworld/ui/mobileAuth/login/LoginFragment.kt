package com.app.sambeautyworld.ui.mobileAuth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.enableLocation.EnableLocationServices
import kotlinx.android.synthetic.main.include_login.*

/**
 * Created by ${Shubham} on 12/26/2018.
 */
class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        btDoneLogin.setOnClickListener {
            replaceFragment(EnableLocationServices(), true, R.id.container_main)
        }
    }
}