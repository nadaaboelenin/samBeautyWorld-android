package com.app.sambeautyworld.ui.sideMenuOpions.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.ui.filters.FilterContentFragment
import kotlinx.android.synthetic.main.fragment_favourite_salons.*

/**
 * Created by ${Shubham} on 1/7/2019.
 */
class FavouritesFragment : BaseFragment(), OnItemClicked {

    private val dummySpecialOffers: ArrayList<String>? = ArrayList()

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        ivFilters.setOnClickListener {
            addFragment(FilterContentFragment(),true,R.id.container_home)
        }
    }

    private fun setUpData() {
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")
        dummySpecialOffers?.add("1")

//        rvFavoriteSalons.adapter = AtTheSalonAdapter(dummySpecialOffers, context!!, this)
//        rvFavoriteSalons.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_salons, container, false)
    }
}