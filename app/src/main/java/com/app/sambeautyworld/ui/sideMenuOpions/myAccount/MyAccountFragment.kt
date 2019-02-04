package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAccountCountries
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import com.app.sambeautyworld.pojo.accountPojo.Salon
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_my_accout.*

/**
 * Created by ${Shubham} on 12/31/2018.
 */
class MyAccountFragment : BaseFragment(), OnItemClicked {
    override fun onItemClick(position: Int) {
//        noOfSalonsPojo!![position].selected = !noOfSalonsPojo!![position].selected!!
//
//
//        for()
        etAddressesAccount.setText(noOfSalonsPojo!![position]!!.country_name)
        for (i in noOfSalonsPojo!!) {
            i.selected = false
        }

        noOfSalonsPojo!![position].selected = true
        myAdapter?.notifyDataSetChanged()
    }

    private var myAdapter: MyAccountCountries? = null
    private var noOfSalonsPojo: ArrayList<Salon>? = ArrayList()
    private var mViewModel: MyAccountModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[MyAccountModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                //showMessage(it.message)
                if (it.status == 1) {
                    setUpData(it)
                } else {
                    showMessage(it.status.toString())
                }
            }
        })

        mViewModel?.apiError?.observe(this, Observer { it ->
            it?.let {
                showSnackBar(it)
            }
        })

        mViewModel?.isLoading?.observe(this, Observer { it ->
            it?.let { showLoading(it) }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitApi()
        clickListeners()
    }

    private fun hitApi() {
        mViewModel?.authenticate(Preferences.prefs?.getString(Constants.ID, "0")!!)
    }

    private fun clickListeners() {

    }

    private fun setUpData(pojo: GetAccountPojo) {

        noOfSalonsPojo = pojo.salons as ArrayList<Salon>
        myAdapter = MyAccountCountries(noOfSalonsPojo, context, this)
        rvTotalNoOfSalons.adapter = myAdapter
        rvTotalNoOfSalons.layoutManager = LinearLayoutManager(this.activity, 1, false)

        etFullNameAccount.setText(pojo.userInformation!!.full_name)
        etAddressesAccount.setText(pojo.userInformation!!.address)
        etEmailsAccount.setText(pojo.userInformation!!.email)
        etBirthDayAccount.setText(pojo.userInformation!!.dob)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_accout, container, false)
    }
}