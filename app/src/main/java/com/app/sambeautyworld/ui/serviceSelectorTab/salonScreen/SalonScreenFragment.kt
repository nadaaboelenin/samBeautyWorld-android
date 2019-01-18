package com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.CustomPagerAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.ui.serviceSelectorTab.salonInformation.SalonInformationFragment
import kotlinx.android.synthetic.main.dialog_opening_hours.*
import kotlinx.android.synthetic.main.fragment_salon_screen.*


/**
 * Created by ${Shubham} on 1/8/2019.
 */
class SalonScreenFragment : BaseFragment(), OnItemClicked {


    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

    override fun onItemClick(position: Int) {
        showSnackBar(position.toString())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        ivAboutSalon.setOnClickListener {
            addFragment(SalonInformationFragment(), true, R.id.container_home)
        }

        ivHours.setOnClickListener {
            openHoursDialog()
        }
    }

    private fun openHoursDialog() {
        val dialog = Dialog(context!!)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_opening_hours)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.ivCloseDialog.setOnClickListener {
            dialog.hide()
        }
        dialog.show()
    }

    private fun setUpData() {
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")

        vpSalonScreenImage.adapter = CustomPagerAdapter(context!!, dummySpecialOffers!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_screen, container, false)
    }
}