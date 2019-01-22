package com.app.sambeautyworld.ui.serviceSelectorTab.salonInformation

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.CustomPagerAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.pojo.salonScreen.SalonScreenPojo
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_salon_information.*


/**
 * Created by ${Shubham} on 1/8/2019.
 */
class SalonInformationFragment : BaseFragment() {

    private var dummySpecialOffers: ArrayList<String>? = ArrayList()
    private var salonScreenPojo = SalonScreenPojo()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundled()
        clickListeners()
    }

    private fun getBundled() {
        if (arguments != null) {
            salonScreenPojo = arguments?.getParcelable(Constants.DIALOG_SHOWN)!!
            dummySpecialOffers = salonScreenPojo.media as ArrayList<String>
            setUpData()
        }
    }

    private fun setUpData() {
        tvNameInformation.text = salonScreenPojo.about!!.salon_name
        tvSalonLocationInformation.text = salonScreenPojo.about!!.salon_location
        tvSalonNumbers.text = salonScreenPojo.about!!.salon_phone
        vpSalonScreenImageInformation.adapter = CustomPagerAdapter(context!!, dummySpecialOffers!!)
        pageIndicatorView_salon_screen.count = dummySpecialOffers!!.size

        vpSalonScreenImageInformation.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView_salon_screen.selection = position
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
        })


    }

    private fun clickListeners() {

    }
}