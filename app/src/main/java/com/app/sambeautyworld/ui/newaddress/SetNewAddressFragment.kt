package com.app.sambeautyworld.ui.newaddress

import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.SelectUsersLocationFragment
import com.app.sambeautyworld.ui.sideMenuOpions.myAccount.MyAccountFragment
import kotlinx.android.synthetic.main.fragment_set_new_address.*

class SetNewAddressFragment : BaseFragment() {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var address: ArrayList<Address> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        getBundled()
    }

    private fun getBundled() {
        if (arguments != null && !arguments?.isEmpty!!) {
            if (arguments?.containsKey("lat")!!) {
                latitude = arguments?.getDouble("lat")!!
                longitude = arguments?.getDouble("lon")!!
                address = getAddress(latitude, longitude)
                setUpData()
            }
        }
    }

    private fun setUpData() {
        etHouseNumber.setText(address[0].premises)
        etStreetName.setText(address[0].adminArea)
        etPostCode.setText(address[0].postalCode)
        etCity.setText(address[0].locality)
        etAreaLine1.setText(address[0].getAddressLine(0))

    }

    private fun clickListeners() {
        cvAddressView.setOnClickListener {
            addFragment(SelectUsersLocationFragment(), true, R.id.container_home)
        }

        btSaveMyAddress.setOnClickListener {
            var setNewAddressFragment = MyAccountFragment()
            var args = Bundle()
            args.putParcelableArrayList("array", address)
            setNewAddressFragment.arguments = args
            replaceFragment(setNewAddressFragment, false, R.id.container_home)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_set_new_address, container, false)
    }


}