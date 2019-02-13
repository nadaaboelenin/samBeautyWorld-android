package com.app.sambeautyworld.ui.sideMenuOpions.sendFeedback

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_send_feedback.*

/**
 * Created by ${Shubham} on 12/31/2018.
 */
class SendFeedbackFragment : BaseFragment() {

    private var mViewModel: SendFeedbackModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[SendFeedbackModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer {
            it?.let {
                showMessage(it.message)
                if (it.status == 1) {
                    goBackWithDelay()
                } else {

                }
            }
        })

        mViewModel?.apiError?.observe(this, Observer {
            it?.let {
                showSnackBar(it)
            }
        })

        mViewModel?.isLoading?.observe(this, Observer {
            it?.let { showLoading(it) }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        btSendFeedback.setOnClickListener {
            mViewModel?.sendFeedback(Preferences.prefs?.getString(Constants.ID, "0")!!, etYourFeedback.text.toString())
        }
    }

    private fun setUpData() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_feedback, container, false)
    }
}