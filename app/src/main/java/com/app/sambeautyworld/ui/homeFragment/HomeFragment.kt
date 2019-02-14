package com.app.sambeautyworld.ui.homeFragment

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.saveValue
import com.hmu.kotlin.callBack.ItemSelectedListener
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.bottom_sheet_content.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.include_bookmarks.*
import kotlinx.android.synthetic.main.include_showing_locations.*


/**
 * Created by ${Shubham} on 12/27/2018.
 */
class HomeFragment : BaseFragment(), OnItemClicked, ItemSelectedListener {
    override fun selectedItem(item: Int, pos: Int) {
        val intent = Intent(activity, ActivitySalonStartPoint::class.java)
        intent.putExtra(Constants.FROM_SEARCH, "1")
        intent.putExtra(Constants.BUSINES_OWNER, dumyBookmark!![pos].ownder_id)
        startActivity(intent)
    }

    private var flagger: Boolean? = false
    private var mViewModel: HomeFragmentModel? = null
    private var id: String? = null

    override fun onItemClick(position: Int) {
        val args = Bundle()
        args.putString(Constants.SERVICE_ID, dummyList!![position].id)
        args.putString(Constants.SERVICE_NAME, dummyList!![position].service_name)
        val intent = Intent(activity, ActivitySalonStartPoint::class.java)
        intent.putExtra(Constants.SERVICE_ID, args)
        startActivity(intent)
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

        getBundled()

        clickListeners()
    }

    private fun getBundled() {
        if (arguments != null) {
            id = arguments!!.getString(Constants.ID)
            hitApi()
        }
    }

    private fun hitApi() {
        /**
         *
         *    Preferences.prefs?.saveValue(Constants.ID, "1")
        mViewModel?.getServices(Preferences.prefs?.getString(Constants.ID, "1")!!, this!!.id!!)
         */
        mViewModel?.getServices(Preferences.prefs?.getString(Constants.ID, "0")!!, this!!.id!!)

    }


    private fun clickListeners() {

        llBottomClicker.setOnClickListener {

            if (flagger!!) {
                flagger = !flagger!!
                BottomSheetBehavior.from(bottom_sheet)
                        .setState(BottomSheetBehavior.STATE_COLLAPSED)
            } else {
                flagger = !flagger!!
                BottomSheetBehavior.from(bottom_sheet)
                        .setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }



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
            val allSpecialOffers = SeeAllOffersFragment()
            val args = Bundle()
            args.putParcelableArrayList(Constants.SPECIAL_OFFERS, dummySpecialOffers)
            allSpecialOffers.arguments = args
            addFragment(allSpecialOffers, true, R.id.container_home)
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
        rvRecyclerAppointments.adapter = BookmarksAdapter(dumyBookmark, context, this)


        //special offers
        vpSpecialOffers.clipToPadding = false
        vpSpecialOffers.setPadding(60, 0, 60, 0)
        vpSpecialOffers.pageMargin = 20
        vpSpecialOffers.adapter = SpecialOffersAdapter(context!!, dummySpecialOffers!!)
        pageIndicatorView.count = dummySpecialOffers!!.size

    }

}