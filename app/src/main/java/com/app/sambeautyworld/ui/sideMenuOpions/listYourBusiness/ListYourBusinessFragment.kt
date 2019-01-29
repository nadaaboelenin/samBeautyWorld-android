package com.app.sambeautyworld.ui.sideMenuOpions.listYourBusiness

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.utils.Validations
import kotlinx.android.synthetic.main.fragment_list_your_business.*


/**
 * Created by ${Shubham} on 12/28/2018.
 */
class ListYourBusinessFragment : BaseFragment() {
    private var mViewModel: ListYourBusinessModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[ListYourBusinessModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    showMessage(it.message)
                    goBackWithDelay()
                } else {
                    showMessage(it.message)
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
        tvGoBackListYourBusiness.setOnClickListener {
            goBack()
        }
        btSubmitBusiness.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        if (Validations.isEmpty(etYourName) && Validations.isEmpty(etYourEmailAddress)
                && Validations.isEmpty(etPhoneNumber) && Validations.isEmpty(etServiceProviderName)) {
            mViewModel?.listBusiness(etYourName.text.toString(), etServiceProviderName.text.toString()
                    , tvCountryCode.selectedCountryCode.toString() + etPhoneNumber.text.toString(), etYourEmailAddress.text.toString())
        }
    }

    private fun setUpData() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_your_business, container, false)
    }
}