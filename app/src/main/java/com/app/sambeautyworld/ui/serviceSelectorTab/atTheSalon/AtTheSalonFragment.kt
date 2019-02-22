package com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.AtTheSalonAdapters
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.salonListBasedOnService.AtTheSalonService
import com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen.SalonScreenFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_at_the_salon.*

/**
 * Created by ${Shubham} on 1/4/2019.
 */
class AtTheSalonFragment : BaseFragment(), OnItemClicked {
    private var mViewModel: SalonServicesModel? = null
    private var id: String? = null
    private var dummySpecialOffers: ArrayList<AtTheSalonService>? = ArrayList()
    private var latitude: Double? = null
    private var longitude: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[SalonServicesModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummySpecialOffers = it.atTheSalonServices as ArrayList<AtTheSalonService>
                    setUpData()
                } else {
                    showLoading(false)
                    showSnackBar("No Results found")
                }
            }
        })


        mViewModel?.responseHomeBased?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummySpecialOffers = it.atTheSalonServices as ArrayList<AtTheSalonService>
                    setUpData()
                } else {
                    showLoading(false)
                    showSnackBar("No Results found")
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

    override fun onItemClick(position: Int) {
        val salonScreenFragment = SalonScreenFragment()
        val args = Bundle()
//        args.putString()

        args.apply {
            putString(Constants.BUSINES_OWNER, dummySpecialOffers!![position].owner_id)
        }
        salonScreenFragment.arguments = args
        addFragment(salonScreenFragment, true, R.id.container_home_salon)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        // setUpData()
        clickListeners()
    }

    private fun getBundle() {
        if (arguments != null) {
            id = arguments?.getString(Constants.SERVICE_ID)
            if (arguments?.containsKey(Constants.LAT)!!) {
                latitude = arguments!!.getDouble(Constants.LAT)
                longitude = arguments!!.getDouble(Constants.LNG)
                fetchDataWithLat()
            } else {
                fetchDataFromApi()
            }
        }
    }

    private fun fetchDataWithLat() {
        mViewModel?.homeBased(Preferences?.prefs?.getString(Constants.ID, "0")!!, id!!, latitude.toString(), longitude.toString())
    }

    private fun fetchDataFromApi() {
        mViewModel?.authenticate(id!!)
    }

    private fun clickListeners() {

    }

    private fun setUpData() {
        rvAtTheSalon.adapter = AtTheSalonAdapters(dummySpecialOffers, context!!, this)
        rvAtTheSalon.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_at_the_salon, container, false)
    }
}