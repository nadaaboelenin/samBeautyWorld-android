package com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen

import Preferences
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.CustomPagerAdapter
import com.app.sambeautyworld.adapter.ExpandableListViewAdapter
import com.app.sambeautyworld.adapter.ExpandableProductListViewAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.AddRemoveListener
import com.app.sambeautyworld.callBack.OnItemClickListener
import com.app.sambeautyworld.pojo.salonScreen.Hours
import com.app.sambeautyworld.pojo.salonScreen.ProductsList
import com.app.sambeautyworld.pojo.salonScreen.SalonScreenPojo
import com.app.sambeautyworld.ui.chatview.ChatSupportFragment
import com.app.sambeautyworld.ui.mapFragment.MapsTrial
import com.app.sambeautyworld.ui.serviceSelectorTab.salonInformation.SalonInformationFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.dialog_more_information_about_product.*
import kotlinx.android.synthetic.main.dialog_opening_hours.*
import kotlinx.android.synthetic.main.fragment_salon_screen.*


/**
 * Created by ${Shubham} on 1/8/2019.
 */
class SalonScreenFragment : BaseFragment(), OnItemClickListener, AddRemoveListener {
    override fun addRemove(item: Int, sub_item: Int, which_screen: Int, i: Int) {
        if (which_screen == 1) {
            salonScreenPojo.servicesList!![item].subServices!![sub_item].count = !salonScreenPojo.servicesList!![item].subServices!![sub_item].count
            expandableListViewAdapter?.notifyDataSetChanged()
        } else {
            salonScreenPojo.productsList!![item].products[sub_item].count = i
            expandableProductListViewAdapter?.notifyDataSetChanged()
        }
    }

    override fun onItemClick(item: Int, sub_item: Int, which_screen: Int) {
        if (which_screen == 1) {

        } else {
            showInformationdialog(item, sub_item)
        }
    }

    private var hours = Hours()
    private var expandableListViewAdapter: ExpandableListViewAdapter? = null
    private var expandableProductListViewAdapter: ExpandableProductListViewAdapter? = null
    private var id: String? = null
    private var lat_long: String? = null
    private var salon_id: String? = null
    private var dummySpecialOffers: ArrayList<String>? = ArrayList()

    private var mViewModel: SalonScreenModel? = null
    private var salonScreenPojo = SalonScreenPojo()
    private var productsList: List<ProductsList>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        clickListeners()
    }

    private fun getBundle() {
        if (arguments != null) {
            id = arguments?.getString(Constants.BUSINES_OWNER)
            fetchDataFromApi()
        }
    }

    private fun fetchDataFromApi() {
        mViewModel?.getAllServices(id!!)
    }

    private fun clickListeners() {

        ivAddToFavourites.setOnClickListener {
            mViewModel?.addBookmark(Preferences?.prefs?.getString(Constants.ID, "0")!!, id!!)
        }

        ivAboutSalon.setOnClickListener {
            val salonInformationFragment = SalonInformationFragment()
            val args = Bundle()
            args.putParcelable(Constants.DIALOG_SHOWN, salonScreenPojo)
            salonInformationFragment.arguments = args
            addFragment(salonInformationFragment, true, R.id.container_home_salon)
        }

        ivHours.setOnClickListener {
            openHoursDialog()
        }

        ivChatWithSalon.setOnClickListener {
            addFragment(ChatSupportFragment(), true, R.id.container_home_salon)
        }

        ivLocateSalonOnMap.setOnClickListener {
            val mapsTrial = MapsTrial()
            val args = Bundle()
            args.putString(Constants.LAT, lat_long)
            mapsTrial.arguments = args
            addFragment(mapsTrial, true, R.id.container_home_salon)
        }


        tvServicesOfSalon.setOnClickListener {
            tvServicesOfSalon.setBackgroundColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
            tvServicesOfSalon.setTextColor(ContextCompat.getColor(context!!, R.color.white))

            tvProductsOfSalons.setBackgroundColor(ContextCompat.getColor(context!!, R.color.grayBackround))
            tvProductsOfSalons.setTextColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
            rv_availableplaces.setAdapter(expandableListViewAdapter)
        }

        tvProductsOfSalons.setOnClickListener {
            tvProductsOfSalons.setBackgroundColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
            tvProductsOfSalons.setTextColor(ContextCompat.getColor(context!!, R.color.white))

            tvServicesOfSalon.setBackgroundColor(ContextCompat.getColor(context!!, R.color.grayBackround))
            tvServicesOfSalon.setTextColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
            rv_availableplaces.setAdapter(expandableProductListViewAdapter)
        }
    }

    private fun openHoursDialog() {
        val dialog = Dialog(context!!)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_opening_hours)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

//        dialog.tvMondayTimings.text=hours.monday
//        dialog.tvTuesdayTiming.text=hours.tuesday
//        dialog.tvWednesdayTiming.text=hours.wednesday
//        dialog.tvThursdayTiming.text=hours.thursday
//        dialog.tvFridayTiming.text=hours.friday
//        dialog.tvSaturdayTiming.text=hours.saturday
//        dialog.tvSundayValue.text=hours.sunday

        dialog.ivCloseDialog.setOnClickListener {
            dialog.hide()
        }
        dialog.show()
    }

    private fun setUpData(it: SalonScreenPojo) {
        productsList = it.productsList as List<ProductsList>
        pageIndicatorView_salon_screen.count = dummySpecialOffers!!.size
        hours = it.hours!!
        vpSalonScreenImage.adapter = CustomPagerAdapter(context!!, dummySpecialOffers!!)

        expandableListViewAdapter = ExpandableListViewAdapter(context!!, salonScreenPojo.servicesList, this, this)
        expandableProductListViewAdapter = ExpandableProductListViewAdapter(context!!, salonScreenPojo.productsList, this, this)
        rv_availableplaces.setAdapter(expandableListViewAdapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_screen, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[SalonScreenModel::class.java]
        attchObservers()
    }

    private fun attchObservers() {
        mViewModel?.responseAllServices?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    dummySpecialOffers = it.media as ArrayList<String>
                    lat_long = it.latitude + "," + it.longitude
                    salonScreenPojo = it
                    setUpData(it)
                } else {

                }
            }
        })

        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    showMessage(it.message)
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

    private fun showInformationdialog(item: Int, sub_item: Int) {

        var dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_more_information_about_product)

        dialog.tvProductsCount.text = salonScreenPojo.productsList!![item].products[sub_item].count.toString()

        dialog.btApplyProducts.setOnClickListener {
            dialog.dismiss()
        }
        dialog.ivCloseMoreInfoDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialog.ivAddProducts.setOnClickListener {
            salonScreenPojo.productsList!![item].products[sub_item].count++
            expandableProductListViewAdapter?.notifyDataSetChanged()
            dialog.tvProductsCount.text = salonScreenPojo.productsList!![item].products[sub_item].count.toString()
        }

        dialog.ivRemoveItems.setOnClickListener {
            salonScreenPojo.productsList!![item].products[sub_item].count--
            expandableProductListViewAdapter?.notifyDataSetChanged()
            dialog.tvProductsCount.text = salonScreenPojo.productsList!![item].products[sub_item].count.toString()
        }


        dialog.show()
    }

}