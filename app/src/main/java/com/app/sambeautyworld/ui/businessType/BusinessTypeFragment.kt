package com.app.sambeautyworld.ui.businessType

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.BusinessAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.businessType.AllBusinessList
import com.app.sambeautyworld.ui.homeFragment.HomeFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_business_type.*

class BusinessTypeFragment : BaseFragment(), OnItemClicked {
    override fun onItemClick(position: Int) {
        val homeFragment = HomeFragment()
        val args = Bundle()
        args.putString(Constants.ID, list[position].id)
        homeFragment.arguments = args
        addFragment(homeFragment, true, R.id.container_home)
    }

    private var list: ArrayList<AllBusinessList> = ArrayList()
    private var mViewModel: GetBusinessModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[GetBusinessModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    list = it.allBusinessList as ArrayList<AllBusinessList>
                    setUpData()
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
        hitApi()
    }

    private fun hitApi() {
        mViewModel?.authenticate()
    }

    private fun setUpData() {
        rvRecyclerBusinessType.layoutManager = LinearLayoutManager(activity)
        rvRecyclerBusinessType.adapter = BusinessAdapter(list, activity, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_business_type, container, false)
    }


}