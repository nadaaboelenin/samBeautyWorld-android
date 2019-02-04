package com.app.sambeautyworld.ui.serviceSelectorTab.cart

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAdapter
import com.app.sambeautyworld.adapter.SubServicesAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.AddRemoveListener
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.salonScreen.Product
import com.app.sambeautyworld.pojo.salonScreen.SubService
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment(), OnItemClicked, AddRemoveListener {

    override fun addRemove(item: Int, sub_item: Int, which_screen: Int, i: Int) {
        if (which_screen == 1) {
            subServices.removeAt(item)
            setServiceList(subServices)
            expandableListViewAdapter?.notifyDataSetChanged()

        }
        //for products only
        else {
            productLists[item].count = i
            if (productLists[item].count == 0) {
                productLists.removeAt(item)
            }
            setProductList(productLists)
            expandableProductListViewAdapter?.notifyDataSetChanged()
        }
    }

    override fun onItemClick(position: Int) {

    }

    private var expandableListViewAdapter: SubServicesAdapter? = null
    private var expandableProductListViewAdapter: MyAdapter? = null
    private var productLists: ArrayList<Product> = ArrayList()
    private var subServices: ArrayList<SubService> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getrBundledArguments()
    }

    private fun getrBundledArguments() {
        productLists = BaseFragment.productList
        subServices = BaseFragment.subServices
        expandableListViewAdapter = SubServicesAdapter(subServices, context!!, this, this)
        expandableProductListViewAdapter = MyAdapter(productLists, context!!, this, this)
        rvCartProducts.adapter = expandableProductListViewAdapter
        rvCartServices.adapter = expandableListViewAdapter
        rvCartProducts.layoutManager = LinearLayoutManager(context!!)
        rvCartServices.layoutManager = LinearLayoutManager(context!!)
        expandableProductListViewAdapter?.notifyDataSetChanged()
        expandableListViewAdapter?.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
}