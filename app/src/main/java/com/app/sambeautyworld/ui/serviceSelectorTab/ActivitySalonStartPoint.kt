package com.app.sambeautyworld.ui.serviceSelectorTab

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseActivity
import com.app.sambeautyworld.ui.serviceSelectorTab.atHome.SelectAreaFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon.AtTheSalonFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.cart.CartFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.information_dialog.InformationDialogFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen.SalonScreenFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.activity_salons.*

class ActivitySalonStartPoint : BaseActivity() {
    private var id: String? = null
    private var service_name: String? = null

    override fun getID(): Int {
        return R.layout.activity_salons
    }

    override fun iniView(savedInstanceState: Bundle?) {
        setUpData()
        getBundle()
        clickListeners()

    }


    private fun getBundle() {
        if (intent != null) {
            if (intent.hasExtra(Constants.FROM_SEARCH)) {
                linearLayout_tab.background = ResourcesCompat.getDrawable(resources, R.drawable.background_gray_tab, null)
                llAtTheSalon.background = ResourcesCompat.getDrawable(resources, R.drawable.tab_background, null)
                llAtTheHome.setBackgroundResource(0)
                ivAtTheHome.setColorFilter(ContextCompat.getColor(this, R.color.greyBackround), android.graphics.PorterDuff.Mode.MULTIPLY);
                ivAtSalonIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                tvSelectSalon.setTextColor(ContextCompat.getColor(this, R.color.white))
                tvSelectHome.setTextColor(ContextCompat.getColor(this, R.color.greyBackround))
                val salonScreenFragment = SalonScreenFragment()
                val args = Bundle()
                args.apply {
                    putString(Constants.BUSINES_OWNER, intent.getStringExtra(Constants.BUSINES_OWNER))
                }
                salonScreenFragment.arguments = args
                replaceFragment(salonScreenFragment, R.id.container_home_salon)
            } else {
                id = intent.getBundleExtra(Constants.SERVICE_ID)[Constants.SERVICE_ID].toString()
                service_name = intent.getBundleExtra(Constants.SERVICE_ID)[Constants.SERVICE_NAME].toString()
                tvSalonServiceName.text = service_name
            }
        }
    }

    private fun setUpData() {
        replaceFragment(InformationDialogFragment(), R.id.container_home_salon)
    }

    private fun clickListeners() {

        llAtTheSalon.setOnClickListener {
            linearLayout_tab.background = ResourcesCompat.getDrawable(resources, R.drawable.background_gray_tab, null)
            llAtTheSalon.background = ResourcesCompat.getDrawable(resources, R.drawable.tab_background, null)
            llAtTheHome.setBackgroundResource(0)
            ivAtTheHome.setColorFilter(ContextCompat.getColor(this, R.color.greyBackround), android.graphics.PorterDuff.Mode.MULTIPLY);
            ivAtSalonIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvSelectSalon.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvSelectHome.setTextColor(ContextCompat.getColor(this, R.color.greyBackround))
            val atTheSalonFragment = AtTheSalonFragment()
            val args = Bundle()
            args.putString(Constants.SERVICE_ID, id)
            atTheSalonFragment.arguments = args
            replaceFragment(atTheSalonFragment, R.id.container_home_salon)
        }

        llAtTheHome.setOnClickListener {
            linearLayout_tab.background = ResourcesCompat.getDrawable(resources, R.drawable.background_gray_tab, null)
            llAtTheHome.background = ResourcesCompat.getDrawable(resources, R.drawable.tab_background, null)
            llAtTheSalon.setBackgroundResource(0)
            ivAtSalonIcon.setColorFilter(ContextCompat.getColor(this, R.color.greyBackround), android.graphics.PorterDuff.Mode.MULTIPLY);
            ivAtTheHome.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvSelectHome.setTextColor(ContextCompat.getColor(this, R.color.white))
            tvSelectSalon.setTextColor(ContextCompat.getColor(this, R.color.greyBackround))
            val atTheSalonFragment = SelectAreaFragment()
            val args = Bundle()
            args.putString(Constants.SERVICE_ID, id)
            atTheSalonFragment.arguments = args
            replaceFragment(atTheSalonFragment, R.id.container_home_salon)
        }

        ivGoBackSalon.setOnClickListener {
            onBackPressed()
        }

        ivGotoCart.setOnClickListener {
            tvSalonServiceName.text = "Cart"
            linearLayout_tab.visibility = View.GONE
            addFragment(CartFragment(), true, R.id.container_home_salon)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        linearLayout_tab.visibility = View.VISIBLE
        tvSalonServiceName.text = service_name
    }
}