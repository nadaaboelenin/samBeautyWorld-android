package com.app.sambeautyworld.ui.filters

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.pojo.filter.GetFilterPojo
import com.app.sambeautyworld.pojo.requestFilters.RequestFilterPojo
import com.app.sambeautyworld.ui.sideMenuOpions.favourites.FavouritesFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_filters.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.textColor


/**
 * Created by ${Shubham} on 1/7/2019.
 */
class FilterContentFragment : BaseFragment() {

    private var at_salon: String = "1";
    private var at_home: String = "0";
    private var at_makeup: String = "0";
    private var latitude: String = "0";
    private var longitude: String = "0";

    private var myViewModel: GetFilterModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel = ViewModelProviders.of(this)[GetFilterModel::class.java]
        attachObservers()
    }

    private var salon: Boolean = false
    private var home: Boolean = false
    private var makeup: Boolean = false

    private fun attachObservers() {
        myViewModel?.response?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    setUpData(it)
                } else {

                }
            }
        })

        myViewModel?.apiError?.observe(this, Observer {
            it?.let {
                showSnackBar(it)
            }
        })

        myViewModel?.isLoading?.observe(this, Observer {
            it?.let { showLoading(it) }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        hitApi()
    }

    private fun hitApi() {
        myViewModel?.getFilters(Preferences.prefs!!.getString(Constants.ID, "0"))
    }

    private fun clickListeners() {
        btApplyFilters.setOnClickListener {
            val requestFilterPojo = RequestFilterPojo(Preferences.prefs!!.getString(Constants.ID, "0"), textMin1.text.toString(),
                    textMax1.text.toString(), at_salon, at_home, at_makeup, latitude, longitude
            )

            val args = Bundle()
            args.putString("filter", "1")
            args.putParcelable("pojo", requestFilterPojo)
            val favouritesFragment = FavouritesFragment()
            favouritesFragment.arguments = args
            replaceFragment(favouritesFragment, true, R.id.container_home)
        }


        tvGoBackFilters.setOnClickListener {
            goBack()
        }

        llSalon.setOnClickListener {
            at_salon = "1"
            at_home = "0"
            at_makeup = "0"
            unselect()
            llSalon.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_selected)
            ivSalonLogo.setColorFilter(ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvSalonText.textColor = ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor)
        }

        llHome.setOnClickListener {
            at_salon = "0"
            at_home = "1"
            at_makeup = "0"
            unselect()

            llHome.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_selected)
            ivHomLogo.setColorFilter(ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvHomeText.textColor = ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor)
        }

        llMakeUpArtist.setOnClickListener {
            at_salon = "0"
            at_home = "0"
            at_makeup = "1"
            unselect()

            llMakeUpArtist.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_selected)
            ivMakeUpArtist.setColorFilter(ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvMakeupText.textColor = ContextCompat.getColor(activity!!, com.app.sambeautyworld.R.color.backgroundColor)
        }
    }

    private fun unselect() {
        llSalon.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_background)
        llHome.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_background)
        llMakeUpArtist.backgroundDrawable = ContextCompat.getDrawable(activity!!, com.app.sambeautyworld.R.drawable.drawable_linear_background)

        ivHomLogo.setColorFilter(ContextCompat.getColor(activity!!, android.R.color.darker_gray), android.graphics.PorterDuff.Mode.MULTIPLY);
        ivSalonLogo.setColorFilter(ContextCompat.getColor(activity!!, android.R.color.darker_gray), android.graphics.PorterDuff.Mode.MULTIPLY);
        ivMakeUpArtist.setColorFilter(ContextCompat.getColor(activity!!, android.R.color.darker_gray), android.graphics.PorterDuff.Mode.MULTIPLY);

        tvSalonText.textColor = ContextCompat.getColor(activity!!, android.R.color.darker_gray)
        tvHomeText.textColor = ContextCompat.getColor(activity!!, android.R.color.darker_gray)
        tvMakeupText.textColor = ContextCompat.getColor(activity!!, android.R.color.darker_gray)
    }

    private fun setUpData(it: GetFilterPojo) {
        var planet_array: ArrayList<String> = ArrayList()

        it.data.location.forEach {
            planet_array.add(it.city)
        }

        val spinnerArrayAdapter = ArrayAdapter<String>(activity!!, com.app.sambeautyworld.R.layout.spinner_item, planet_array)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line) // The drop down view
        spinner.adapter = spinnerArrayAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                latitude = it.data.location[position].latitude
                longitude = it.data.location[position].longitude
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        rangeSeekbar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            textMin1.text = minValue.toString()
            textMax1.text = maxValue.toString()
        }

        rangeSeekbar.setOnRangeSeekbarFinalValueListener { minValue, maxValue -> Log.d("CRS=>", "$minValue : $maxValue") }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_filters, container, false)
    }
}