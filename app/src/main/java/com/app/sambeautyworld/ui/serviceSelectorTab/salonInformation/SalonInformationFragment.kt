package com.app.sambeautyworld.ui.serviceSelectorTab.salonInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.CustomPagerAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import kotlinx.android.synthetic.main.fragment_salon_information.*

/**
 * Created by ${Shubham} on 1/8/2019.
 */
class SalonInformationFragment : BaseFragment() {

    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun setUpData() {
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")

        vpSalonScreenImageInformation.adapter = CustomPagerAdapter(context!!, dummySpecialOffers!!)
    }

    private fun clickListeners() {

    }
}