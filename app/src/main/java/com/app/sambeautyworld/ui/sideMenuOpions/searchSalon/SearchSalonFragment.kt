package com.app.sambeautyworld.ui.sideMenuOpions.searchSalon

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
import com.app.sambeautyworld.dummyData.DummySalonList
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by ${Shubham} on 12/28/2018.
 */

class SearchSalonFragment : BaseFragment() {
    private val dummySalonList: ArrayList<DummySalonList> = ArrayList()
    var searchSalonAdapter: SearchSalonAdapter = SearchSalonAdapter(dummySalonList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpData()

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
        val filtered: ArrayList<DummySalonList> = ArrayList()
        for (s in dummySalonList) {
            //if the existing elements contains the search input
            if (s.text_salon_name.toLowerCase().contains(text.toLowerCase())) {
                filtered.add(s)
            }
        }
        searchSalonAdapter.filterList(filtered)

    }

    private fun setUpData() {

        dummySalonList.add(DummySalonList("As you wish", R.mipmap.first_salon, "Salon & Spa"))
        dummySalonList.add(DummySalonList("Aluna", R.mipmap.pic, "Salon & Spa"))
        dummySalonList.add(DummySalonList("The salon", R.mipmap.salon, "Salon & Spa"))
        dummySalonList.add(DummySalonList("Orlando", R.mipmap.orlando, "Salon & Spa"))
        dummySalonList.add(DummySalonList("Sola", R.mipmap.sola, "Salon & Spa"))
        dummySalonList.add(DummySalonList("Aluna", R.mipmap.aluna, "Salon & Spa"))
        dummySalonList.add(DummySalonList("The salon", R.mipmap.salon, "Salon & Spa"))

        rvSalonList.layoutManager = LinearLayoutManager(this!!.activity, 1, false)
        rvSalonList.adapter = searchSalonAdapter

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}