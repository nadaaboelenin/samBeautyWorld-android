package com.app.sambeautyworld.ui.serviceSelectorTab.atHome
import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.SelectAreaAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.salonLocations.Datum
import com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon.AtTheSalonFragment
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.LocationService
import kotlinx.android.synthetic.main.fragment_at_the_home.*

/**
 * Created by ${Shubham} on 1/4/2019.
 */
class SelectAreaFragment : BaseFragment(), OnItemClicked {

    private var dummySpecialOffers: ArrayList<Datum>? = ArrayList()
    private var mViewModel: AvailableAtHomeModel? = null
    private var id: String? = null
    private var latitude: Double? = null
    private var longitude: Double? = null


    override fun onItemClick(position: Int) {
        latitude = dummySpecialOffers!![position].latitude.toDouble()
        longitude = dummySpecialOffers!![position].longitude.toDouble()
        nextScreen()
    }

    private fun nextScreen() {
        val args = Bundle()
        args.putString(Constants.SERVICE_ID, id)
        args.putDouble(Constants.LAT, latitude!!)
        args.putDouble(Constants.LNG, longitude!!)
        val atTheSalonFragment = AtTheSalonFragment()
        atTheSalonFragment.arguments = args
        addFragment(atTheSalonFragment, true, R.id.container_home_salon)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[AvailableAtHomeModel::class.java]
        attachObservers()
    }

    private fun getBundle() {
        if (arguments != null) {
            id = arguments?.getString(Constants.SERVICE_ID)
            hitApi()
        }
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummySpecialOffers = it.data as ArrayList<Datum>
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        clickListeners()
    }

    private fun hitApi() {
        mViewModel?.getSalonLocations(Preferences.prefs?.getString(Constants.ID, "0")!!, id!!)
    }

    private fun clickListeners() {
        tvSelectMyLocation.setOnClickListener {

            LocationService.getLocation(activity!!, { location ->
                latitude = location.latitude
                longitude = location.longitude
                nextScreen()
            }, {
                showSnackBar("Cannot fetch the locaton, please select the salons manually")
            })
        }
    }

    private fun setUpData() {
        rvLocations.adapter = SelectAreaAdapter(dummySpecialOffers, context!!, this)
        rvLocations.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_at_the_home, container, false)
    }
}