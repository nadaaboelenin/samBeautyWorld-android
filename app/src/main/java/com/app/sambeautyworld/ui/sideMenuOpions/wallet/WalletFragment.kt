package com.app.sambeautyworld.ui.sideMenuOpions.wallet

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.CardsInfoAdapter
import com.app.sambeautyworld.adapter.WalletAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import kotlinx.android.synthetic.main.fragment_wallet.*

/**
 * Created by ${Shubham} on 1/8/2019.
 */
class WalletFragment : BaseFragment(), OnItemClicked {
    override fun onItemClick(position: Int) {

    }

    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

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


        rvCards.adapter = CardsInfoAdapter(dummySpecialOffers, context!!, this)
        rvCards.layoutManager = LinearLayoutManager(context!!, 1, false)


        rvTransactionHistory.adapter = WalletAdapter(dummySpecialOffers, context!!, this)
        rvTransactionHistory.layoutManager = LinearLayoutManager(context!!, 1, false)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }
}