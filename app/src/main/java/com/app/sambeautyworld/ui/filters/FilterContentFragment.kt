package com.app.sambeautyworld.ui.filters

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.pojo.filter.GetFilterPojo
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_filters.*


/**
 * Created by ${Shubham} on 1/7/2019.
 */
class FilterContentFragment : BaseFragment() {
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
        tvGoBackFilters.setOnClickListener {
            goBack()
        }

        llSalon.setOnClickListener {
            if (salon) {

            } else {

            }
        }

        llHome.setOnClickListener {

        }

        llMakeUpArtist.setOnClickListener {

        }
    }

    private fun setUpData(it: GetFilterPojo) {

        val spinnerArrayAdapter = ArrayAdapter<String>(activity!!, R.layout.spinner_item, it.data.all_locaiton)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line) // The drop down view
        spinner.adapter = spinnerArrayAdapter

//        var planet_array: ArrayList<String> = it.data.location as ArrayList<String>
//        ArrayAdapter.createFromResource(
//                activity!!,
//                planet_array,
//                android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_filters, container, false)
    }
}