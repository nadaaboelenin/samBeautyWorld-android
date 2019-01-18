package com.app.sambeautyworld.ui.homeFragment

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.app.sambeautyworld.pojo.listServices.FeaturedServicesList
import com.app.sambeautyworld.pojo.mainHome.BookMark
import com.app.sambeautyworld.pojo.mainHome.SpecialOffer
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

    private var mViewModel: HomeFragmentModel? = null

    override fun onItemClick(position: Int) {
        activity?.toolbar?.visibility = View.GONE
        addFragment(HomeSalonSelectorFragment(), true, R.id.container_home)
    }

    private var dummyList: ArrayList<FeaturedServicesList>? = ArrayList()
    private var dumyBookmark: ArrayList<BookMark>? = ArrayList()
    private var dummySpecialOffers: ArrayList<SpecialOffer>? = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[HomeFragmentModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {

        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummyList = it?.featuredServicesList as ArrayList<FeaturedServicesList>
                    if (it.bookMarks?.size != null) {
                        dumyBookmark = it?.bookMarks as ArrayList<BookMark>
                    }

                    if (it.specialOffers?.size != null) {
                        dummySpecialOffers = it?.specialOffers as ArrayList<SpecialOffer>
                    }
                    setUpData()
                } else {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_coordinator_test, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hitApi()
        clickListeners()
    }

    private fun hitApi() {
        mViewModel?.getServices(Preferences.prefs?.getString(Constants.ID, "0")!!)
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

        //featured services
        rvFeaturedServices.layoutManager = GridLayoutManager(activity, 4)
        rvFeaturedServices.adapter = FeaturedServicesAdapter(dummyList, this)


        //bookmarks
        rvRecyclerAppointments.layoutManager = LinearLayoutManager(activity, 0, false)
        rvRecyclerAppointments.adapter = BookmarksAdapter(dumyBookmark, context)


        //special offers
        vpSpecialOffers.clipToPadding = false
        vpSpecialOffers.setPadding(60, 0, 60, 0)
        vpSpecialOffers.pageMargin = 20
        vpSpecialOffers.adapter = SpecialOffersAdapter(context!!, dummySpecialOffers!!)

    }

}