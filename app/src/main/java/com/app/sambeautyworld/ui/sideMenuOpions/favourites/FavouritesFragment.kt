package com.app.sambeautyworld.ui.sideMenuOpions.favourites

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.ui.filters.FilterContentFragment
import com.app.sambeautyworld.ui.homeFragment.HomeFragmentModel
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_favourite_salons.*

/**
 * Created by ${Shubham} on 1/7/2019.
 */
class FavouritesFragment : BaseFragment(), OnItemClicked {
    private var mViewModel: HomeFragmentModel? = null
    private var dummySpecialOffers: ArrayList<com.app.sambeautyworld.pojo.getbookmark.BookMark>? = ArrayList()

    override fun onItemClick(position: Int) {
        val intent = Intent(activity, ActivitySalonStartPoint::class.java)
        intent.putExtra(Constants.FROM_SEARCH, "1")
        intent.putExtra(Constants.BUSINES_OWNER, dummySpecialOffers!![position].ownder_id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[HomeFragmentModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response_bookmark?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    if (it.bookMarks?.size != null) {
                        dummySpecialOffers = it?.bookMarks as ArrayList<com.app.sambeautyworld.pojo.getbookmark.BookMark>
                        setUpData()
                    }
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
        hitAPi()
        clickListeners()
    }

    private fun hitAPi() {
        mViewModel?.getBookmark(Preferences.prefs!!.getString(Constants.ID, "0"))
    }

    private fun clickListeners() {
        ivFilters.setOnClickListener {
            addFragment(FilterContentFragment(), true, R.id.container_home)
        }

        tvGoBackFavourites.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    private fun setUpData() {
        rvFavoriteSalons.adapter = MyAdapter(dummySpecialOffers, context!!, this)
        rvFavoriteSalons.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_salons, container, false)
    }
}