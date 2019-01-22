package com.app.sambeautyworld.ui.serviceSelectorTab

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.atHome.SelectAreaFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon.AtTheSalonFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.information_dialog.InformationDialogFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_tab_salon_home_selector.*

/**
 * Created by ${Shubham} on 1/4/2019.
 */
class HomeSalonSelectorFragment : BaseFragment() {
    private var id: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_salon_home_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        clickListeners()
        setUpData()
    }

    private fun getBundle() {
        if (arguments != null) {
            id = arguments?.getString(Constants.SERVICE_ID)
        }
    }

    private fun setUpData() {
        replaceFragment(InformationDialogFragment(), false, R.id.container_home_salon)
    }

    private fun clickListeners() {

        llAtTheSalon.setOnClickListener {
            activity!!.toolbar.visibility = View.VISIBLE
            linearLayout_tab.background = ResourcesCompat.getDrawable(resources, R.drawable.background_gray_tab, null)
            llAtTheSalon.background = ResourcesCompat.getDrawable(resources, R.drawable.tab_background, null)
            llAtTheHome.setBackgroundResource(0)
            ivAtTheHome.setColorFilter(ContextCompat.getColor(context!!, R.color.greyBackround), android.graphics.PorterDuff.Mode.MULTIPLY);
            ivAtSalonIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvSelectSalon.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            tvSelectHome.setTextColor(ContextCompat.getColor(context!!, R.color.greyBackround))

            val atTheSalonFragment = AtTheSalonFragment()
            val args = Bundle()
            args.putString(Constants.SERVICE_ID, id)
            atTheSalonFragment.arguments = args

            replaceFragment(atTheSalonFragment, false, R.id.container_home_salon)

        }

        llAtTheHome.setOnClickListener {
            activity!!.toolbar.visibility = View.VISIBLE
            linearLayout_tab.background = ResourcesCompat.getDrawable(resources, R.drawable.background_gray_tab, null)
            llAtTheHome.background = ResourcesCompat.getDrawable(resources, R.drawable.tab_background, null)
            llAtTheSalon.setBackgroundResource(0)
            ivAtSalonIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.greyBackround), android.graphics.PorterDuff.Mode.MULTIPLY);
            ivAtTheHome.setColorFilter(ContextCompat.getColor(context!!, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvSelectHome.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            tvSelectSalon.setTextColor(ContextCompat.getColor(context!!, R.color.greyBackround))
            replaceFragment(SelectAreaFragment(), false, R.id.container_home_salon)
        }
    }
}