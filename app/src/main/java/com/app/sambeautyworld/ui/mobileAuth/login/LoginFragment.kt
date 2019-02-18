package com.app.sambeautyworld.ui.mobileAuth.login

import Preferences
import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.activity.MainActivity
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.enableLocation.EnableLocationServices
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.Validations
import com.app.sambeautyworld.utils.saveValue
import com.app.sambeautyworld.utils.social.FacebookModel
import com.app.sambeautyworld.utils.social.SocialLoginManager
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.include_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ${Shubham} on 12/26/2018.
 */
class LoginFragment : BaseFragment() {
    private var mViewModel: SignUpModel? = null
    var dateSelected = Calendar.getInstance()
    private var datePickerDialog: DatePickerDialog? = null
    private var mListeners: SocialLoginManager.SocialLoginListener? = null
    private var newToken: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        if (activity is MainActivity) {
            mListeners = activity as MainActivity
        }
        mViewModel = ViewModelProviders.of(this)[SignUpModel::class.java]
        attachObservers()

    }

    private fun attachObservers() {

        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    Preferences.prefs!!.saveValue(Constants.IS_LOGGED_IN, true)
                    Preferences.prefs!!.saveValue(Constants.ID, it.user_info!!.user_id)
                    replaceFragment(EnableLocationServices(), true, R.id.container_main)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun setUpData() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity!!) { instanceIdResult ->
            newToken = instanceIdResult.token
        }
    }

    private fun clickListeners() {
        btDoneLogin.setOnClickListener {
            validateData()
        }

        btFacebook.setOnClickListener {
            mListeners?.onFacbookLogin()
        }

        btGoogle.setOnClickListener {
            mListeners?.onGoogleLogin()
        }

        etBirthDay.isFocusable = false

        etBirthDay.setOnClickListener {
            DatePickerDialog(activity!!, date, dateSelected
                    .get(Calendar.YEAR), dateSelected.get(Calendar.MONTH),
                    dateSelected.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

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
        etBirthDay.setText(sdf.format(dateSelected.time))
    }

    private fun validateData() {
        if (Validations.isEmpty(etFullName)
                && Validations.isEmpty(etEmails) &&
                Validations.isEmpty(etBirthDay)) {
            if (Validations.isValidEmail(etEmails)) {
                mViewModel?.registerUser(
                        Preferences.prefs?.getString(Constants.PHONE_NUMBER, "0")!!,
                        etFullName.text.toString(), etAddresses.text.toString(), etEmails.text.toString(), etBirthDay.text.toString(),
                        "1", "0", "", newToken
                )
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(model: FacebookModel) {
        hitRegisterApi(model)
    }

    private fun hitRegisterApi(model: FacebookModel) {
        mViewModel?.registerUser(
                Preferences.prefs?.getString(
                        Constants.PHONE_NUMBER, "0")!!,
                model.firstName, "", model.email, "", "1", model.type, model.accessToken.toString(),
                newToken
        )
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Unregister")
        EventBus.getDefault().unregister(this)
    }


}