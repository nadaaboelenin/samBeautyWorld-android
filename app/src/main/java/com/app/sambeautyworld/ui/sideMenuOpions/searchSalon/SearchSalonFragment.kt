package com.app.sambeautyworld.ui.sideMenuOpions.searchSalon

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.SearchSalonAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.searchsallonpojo.AllSalonList
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by ${Shubham} on 12/28/2018.
 */

class SearchSalonFragment : BaseFragment(), OnItemClicked {
    override fun onItemClick(position: Int) {
        /*      var args*/
        val intent = Intent(activity, ActivitySalonStartPoint::class.java)
        intent.putExtra(Constants.FROM_SEARCH, "1")
        intent.putExtra(Constants.BUSINES_OWNER, dummySalonList[position].owner_id)
        startActivity(intent)
    }

    private val dummySalonList: ArrayList<AllSalonList> = ArrayList()
    private var searchSalonAdapter: SearchSalonAdapter = SearchSalonAdapter(dummySalonList, this)
    private var mViewModel: SearchSaloonModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[SearchSaloonModel::class.java]
        attachObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        hitApiTogetAllSalons()
        clickListeners()

    }

    private fun clickListeners() {
        tvGoBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                //showMessage(it.message)
                if (it.status == 1) {
                    dummySalonList.addAll(it.allSalonList)
                    rvSalonList.layoutManager = LinearLayoutManager(this!!.activity, 1, false)
                    rvSalonList.adapter = searchSalonAdapter
                } else {
                    showMessage(it.status.toString())
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


    private fun hitApiTogetAllSalons() {
        mViewModel!!.getAllSalons()
    }

    private fun initViews() {
        etSearchSalon.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filtered: ArrayList<AllSalonList> = ArrayList()
        for (s in dummySalonList) {
            //if the existing elements contains the search input
            if (s.business_name.toLowerCase().contains(text.toLowerCase())) {
                filtered.add(s)
            }
        }
        searchSalonAdapter.filterList(filtered)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}