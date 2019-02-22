package com.app.sambeautyworld.ui.seeOffers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.SeeAllOffersAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.mainHome.SpecialOffer
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_see_all_offers.*

/**
 * Created by ${Shubham} on 1/3/2019.
 */
class SeeAllOffersFragment : BaseFragment() , OnItemClicked {

    override fun onItemClick(position: Int) {
        showSnackBar(position.toString())
    }

    private var dummySpecialOffers: ArrayList<SpecialOffer>? = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        tvGoBackSeeAllOffers.setOnClickListener {
            goBack()
        }
    }

    private fun setUpData() {

        if (!arguments!!.isEmpty) {
            dummySpecialOffers = arguments!!.getParcelableArrayList(Constants.SPECIAL_OFFERS)
        }

        rvSeeAllOffers.layoutManager = LinearLayoutManager(context!!, 1, false)
        rvSeeAllOffers.adapter = SeeAllOffersAdapter(dummySpecialOffers, context!!,this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_see_all_offers, container, false)
    }
}