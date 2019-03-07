package com.app.sambeautyworld.ui.sideMenuOpions.myAccount
import Preferences
import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.location.Address
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAccountCountries
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import com.app.sambeautyworld.pojo.accountPojo.Salon
import com.app.sambeautyworld.ui.newaddress.SetNewAddressFragment
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.dialog_xmls.*
import kotlinx.android.synthetic.main.fragment_my_accout.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ${Shubham} on 12/31/2018.
 */
class MyAccountFragment : BaseFragment(), OnItemClicked {
    private var address: ArrayList<Address> = ArrayList()

    override fun onItemClick(position: Int) {
//        noOfSalonsPojo!![position].selected = !noOfSalonsPojo!![position].selected!!
//
//
//        for()
        etAddressesAccount.setText(noOfSalonsPojo!![position].country_name!!)
        for (i in noOfSalonsPojo!!) {
            i.selected = false
        }

        noOfSalonsPojo!![position].selected = true
        myAdapter?.notifyDataSetChanged()


        mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, "", noOfSalonsPojo!![position].country_name!!, "", "")
    }

    private var myAdapter: MyAccountCountries? = null
    private var noOfSalonsPojo: ArrayList<Salon>? = ArrayList()
    private var mViewModel: MyAccountModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[MyAccountModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                //showMessage(it.message)
                if (it.status == 1) {
                    setUpData(it)
                } else {
                    showMessage(it.status.toString())
                }
            }
        })

        mViewModel?.response_edit?.observe(this, Observer { it ->
            it?.let {
                //showMessage(it.message)
                if (it.status == 1) {
                    mViewModel?.authenticate(Preferences.prefs?.getString(Constants.ID, "0")!!)
                } else {
                    showMessage(it.status.toString())
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
        clickListeners()
        getBunlded()


    }

    private fun getBunlded() {
        if (arguments != null && !arguments?.isEmpty!!) {
            if (arguments?.containsKey("array")!!) {
                address = arguments!!.getParcelableArrayList("array")
                etAddressesAccount.setText(address[0].adminArea)
                mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, "", etAddressesAccount.text.toString(), "", "")
            }
        }
    }

    private fun hitApi() {
        mViewModel?.authenticate(Preferences.prefs?.getString(Constants.ID, "0")!!)
    }

    var dateSelected = Calendar.getInstance()
    private var datePickerDialog: DatePickerDialog? = null
    var date: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        // TODO Auto-generated method stub
        dateSelected.set(Calendar.YEAR, year)
        dateSelected.set(Calendar.MONTH, monthOfYear)
        dateSelected.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etBirthDayAccount.setText(sdf.format(dateSelected.time))
        mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, "", "", "", etBirthDayAccount.text.toString())

    }

    private fun clickListeners() {
        etFullNameAccount.setOnClickListener {
            openDialog(1, "Full Name", view!!)
        }

        etEmailsAccount.setOnClickListener {
            openDialog(3, "Email ID", view!!)
        }

        tvGoBackMyAccount.setOnClickListener {
            goBack()
        }

        etBirthDayAccount.setOnClickListener {
            DatePickerDialog(activity!!, date, dateSelected
                    .get(Calendar.YEAR), dateSelected.get(Calendar.MONTH),
                    dateSelected.get(Calendar.DAY_OF_MONTH)).show()
        }

        etAddressesAccount.setOnClickListener {
            //            addFragment()
            addFragment(SetNewAddressFragment(), true, R.id.container_fullscreen)
        }


    }

    private fun openDialog(i: Int, s: String, view: View) {
        val layoutInflater = activity!!.layoutInflater
        val view = layoutInflater.inflate(com.app.sambeautyworld.R.layout.dialog_xmls, null)
        val alertDialog = AlertDialog.Builder(activity!!).create()
        alertDialog.setTitle("Edit $s")
        alertDialog.setCancelable(false)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, resources.getString(com.app.sambeautyworld.R.string.ok)) { dialog, which ->
            hitApiAccount(i, s, alertDialog.etComments.text.toString())
            dialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, resources.getString(com.app.sambeautyworld.R.string.cancel)) { dialog, which -> dialog.dismiss() }
        alertDialog.setView(view)
        alertDialog.show()
    }


    private fun hitApiAccount(i: Int, s: String, toString: String) {
        when (i) {
            1 -> {
                mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, toString, "", "", "")
            }
            2 -> {

            }
            3 -> {
                mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, "", "", toString, "")
            }
            4 -> {
                mViewModel?.update(Preferences?.prefs?.getString(Constants.ID, "0")!!, "", "", "", s)
            }
        }
    }

    private fun setUpData(pojo: GetAccountPojo) {
        noOfSalonsPojo = pojo.salons as ArrayList<Salon>
        myAdapter = MyAccountCountries(noOfSalonsPojo, context, this)
        rvTotalNoOfSalons.adapter = myAdapter
        rvTotalNoOfSalons.layoutManager = LinearLayoutManager(this.activity, 1, false)
        etFullNameAccount.setText(pojo.userInformation!!.full_name)
        etAddressesAccount.setText(pojo.userInformation!!.address)
        etEmailsAccount.setText(pojo.userInformation!!.email)
        etBirthDayAccount.setText(pojo.userInformation!!.dob)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_my_accout, container, false)
    }
}