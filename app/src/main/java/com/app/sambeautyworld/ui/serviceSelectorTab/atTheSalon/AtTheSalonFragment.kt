package com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.AtTheSalonAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import kotlinx.android.synthetic.main.fragment_at_the_salon.*

/**
 * Created by ${Shubham} on 1/4/2019.
 */
class AtTheSalonFragment : BaseFragment(), OnItemClicked {

    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

    override fun onItemClick(position: Int) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {

    }

    private fun setUpData() {
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")

        rvAtTheSalon.adapter = AtTheSalonAdapter(dummySpecialOffers, context!!, this)
        rvAtTheSalon.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_at_the_salon, container, false)
    }
}