package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAccountCountries
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.dummyData.NoOfSalonsPojo
import kotlinx.android.synthetic.main.fragment_my_accout.*

/**
 * Created by ${Shubham} on 12/31/2018.
 */
class MyAccountFragment : BaseFragment() {
    private var noOfSalonsPojo: ArrayList<NoOfSalonsPojo>? = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {

    }

    private fun setUpData() {
        noOfSalonsPojo?.add(NoOfSalonsPojo("Dubai","148 salons",false))
        noOfSalonsPojo?.add(NoOfSalonsPojo("United Arab Emirates","148 salons",true))
        noOfSalonsPojo?.add(NoOfSalonsPojo("Abu Dhabi","148 salons",false))
        noOfSalonsPojo?.add(NoOfSalonsPojo("Kuwait","148 salons",false))

        rvTotalNoOfSalons.adapter=MyAccountCountries(noOfSalonsPojo,context)
        rvTotalNoOfSalons.layoutManager= LinearLayoutManager(this.activity,1,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_accout, container, false)
    }
}