package com.app.sambeautyworld.ui.homeFragment

import Preferences
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.BookmarksAdapter
import com.app.sambeautyworld.adapter.FeaturedServicesAdapter
import com.app.sambeautyworld.adapter.SpecialOffersAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.dummyData.DummyBookmarks
import com.app.sambeautyworld.dummyData.DummyFeaturedServices
import com.app.sambeautyworld.ui.appointments.AppointmentFragment
import com.app.sambeautyworld.ui.seeOffers.SeeAllOffersFragment
import com.app.sambeautyworld.ui.serviceSelectorTab.HomeSalonSelectorFragment
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.saveValue
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.bottom_sheet_content.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.include_bookmarks.*
import kotlinx.android.synthetic.main.include_showing_locations.*


/**
 * Created by ${Shubham} on 12/27/2018.
 */
class HomeFragment : BaseFragment(), OnItemClicked {

    override fun onItemClick(position: Int) {
        activity?.toolbar?.visibility = View.GONE
        addFragment(HomeSalonSelectorFragment(), true, R.id.container_home)
    }

    private val dummyList: ArrayList<DummyFeaturedServices>? = ArrayList()
    private val dumyBookmark: ArrayList<DummyBookmarks>? = ArrayList()
    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_coordinator_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        addSpecialOffers()
        clickListeners()
    }

    private fun addSpecialOffers() {
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")

        vpSpecialOffers.clipToPadding = false
        // set padding manually, the more you set the padding the more you see of prev & next page
        vpSpecialOffers.setPadding(60, 0, 60, 0)
        // sets a margin b/w individual pages to ensure that there is a gap b/w them
        vpSpecialOffers.pageMargin = 20


        vpSpecialOffers.adapter = SpecialOffersAdapter(context!!, dummySpecialOffers!!)
    }

    private fun clickListeners() {
        btGotIt.setOnClickListener {
            Preferences.prefs?.saveValue(Constants.GOT_IT, true)
            include2.visibility = View.INVISIBLE
        }

        btAppointments.setOnClickListener {
            activity?.toolbar?.visibility = View.GONE
            addFragment(AppointmentFragment(), true, R.id.container_home)
        }

        btViewAllSpecialOffers.setOnClickListener {
            activity?.toolbar?.visibility = View.GONE
            addFragment(SeeAllOffersFragment(), true, R.id.container_home)
        }


    }

    private fun setUpData() {

        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior.isHideable = false


        if (!Preferences.prefs?.getBoolean(Constants.GOT_IT, false)!!) {
            include2.visibility = View.INVISIBLE
            include_bookmarks.visibility = View.VISIBLE
        } else {
            include2.visibility = View.VISIBLE
            include_bookmarks.visibility = View.INVISIBLE
        }

        dummyList?.add(DummyFeaturedServices("Nails", R.mipmap.nails))
        dummyList?.add(DummyFeaturedServices("Massage", R.mipmap.massage))
        dummyList?.add(DummyFeaturedServices("Hairdo", R.mipmap.hairdo))
        dummyList?.add(DummyFeaturedServices("Blowout", R.mipmap.blowout))
        dummyList?.add(DummyFeaturedServices("Make up", R.mipmap.women_makeup))
        dummyList?.add(DummyFeaturedServices("Dermatology", R.mipmap.dermatol))
        dummyList?.add(DummyFeaturedServices("Hair cut", R.mipmap.hair_cut))
        dummyList?.add(DummyFeaturedServices("Tattoo", R.mipmap.tattoo_service))

        rvFeaturedServices.layoutManager = GridLayoutManager(activity, 4)
        rvFeaturedServices.adapter = FeaturedServicesAdapter(dummyList, this)

        dumyBookmark?.add(DummyBookmarks(R.mipmap.salon, "The Salon"))
        dumyBookmark?.add(DummyBookmarks(R.mipmap.eclipse_bookmarks, " "))
        dumyBookmark?.add(DummyBookmarks(R.mipmap.eclipse_bookmarks, " "))

        rvRecyclerAppointments.layoutManager = LinearLayoutManager(activity, 0, false)
        rvRecyclerAppointments.adapter = BookmarksAdapter(dumyBookmark, context)

    }
}