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
import com.app.sambeautyworld.adapter.AtTheSalonAdapter
import com.app.sambeautyworld.adapter.FavouritesAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.filteredsearch.FilteredSearchPojo
import com.app.sambeautyworld.pojo.getbookmark.BookMark
import com.app.sambeautyworld.pojo.requestFilters.RequestFilterPojo
import com.app.sambeautyworld.ui.displaySalons.DisplaySalons
import com.app.sambeautyworld.ui.filters.FilterContentFragment
import com.app.sambeautyworld.ui.homeFragment.HomeFragmentModel
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import com.hmu.kotlin.callBack.OptionSelected
import kotlinx.android.synthetic.main.fragment_favourite_salons.*


/**
 * Created by ${Shubham} on 1/7/2019.
 */
class FavouritesFragment : BaseFragment(), OnItemClicked, OptionSelected {

    override fun selectedItem(item: String) {
        val intent = Intent(activity, ActivitySalonStartPoint::class.java)
        intent.putExtra(Constants.FROM_SEARCH, "1")
        intent.putExtra(Constants.BUSINES_OWNER, item)
        startActivity(intent)
    }

    private var mViewModel: HomeFragmentModel? = null
    private lateinit var requestFilterPojo: RequestFilterPojo
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

        mViewModel?.response_filtered?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummySpecialOffers = it.data as ArrayList<BookMark>
                    setupNew(it)

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

    private fun setupNew(it: FilteredSearchPojo) {
        rvFavoriteSalons.adapter = AtTheSalonAdapter(it.data, context!!, this)
        rvFavoriteSalons.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundled()

        clickListeners()
    }

    private fun getBundled() {
        try {
            if (!arguments?.isEmpty!!) {
                if (arguments?.containsKey("filter")!!) {
                    requestFilterPojo = arguments!!.getParcelable("pojo")
                    hitGetFltered()
                }
            } else {
                hitAPi()
            }
        } catch (e: Exception) {
            hitAPi()
        }

    }

    private fun hitGetFltered() {
        mViewModel?.getFiltered(Preferences.prefs!!.getString(Constants.ID, "0"), requestFilterPojo.min_price
                , requestFilterPojo.max_price, requestFilterPojo.at_salon, requestFilterPojo.at_home, requestFilterPojo.at_makeup, requestFilterPojo.latitude,
                requestFilterPojo.longitude
        )
    }

    private fun hitAPi() {
        mViewModel?.getBookmark(Preferences.prefs!!.getString(Constants.ID, "0"))
    }

    private fun clickListeners() {
        ivFilters.setOnClickListener {
            addFragment(FilterContentFragment(), false, com.app.sambeautyworld.R.id.container_home)
        }

        tvGoBackFavourites.setOnClickListener {
            activity!!.onBackPressed()
        }

        ivLocateOnMap.setOnClickListener {
            addFragment(DisplaySalons(), true, R.id.container_home)
        }
    }

    private fun setUpData() {
        rvFavoriteSalons.adapter = FavouritesAdapter(dummySpecialOffers, context!!, this)
        rvFavoriteSalons.layoutManager = LinearLayoutManager(context!!, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_favourite_salons, container, false)
    }
}