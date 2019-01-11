package com.app.sambeautyworld.ui.mobileAuth.enterOtp

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.mobileAuth.otpVerified.OtpVerifiedFragment
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.saveValue
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.opt_included.*
import java.util.concurrent.TimeUnit


/**
 * Created by ${Shubham} on 12/26/2018.
 */
class OtpVerificationFragment : BaseFragment() {
    private var mVerificationId: String? = null
    private var mAuth: FirebaseAuth? = null
    private var phone_number: String? = null
    private var mViewModel: LoginModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[LoginModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {

        mViewModel?.response?.observe(this, Observer {
            it?.let {
                val message = "${it.message}"
//                showSnackBar(message)
                if (it.status == 1) {
                    Preferences.prefs!!.saveValue(Constants.IS_LOGGED_IN, true)
                    Preferences.prefs!!.saveValue(Constants.ID, it.info!!.userId)
                    Preferences.prefs!!.saveValue(Constants.PHONE_NUMBER, phone_number)
                    replaceFragment(OtpVerifiedFragment(), true, R.id.container_main)

                } else {
                    replaceFragment(OtpVerifiedFragment(), true, R.id.container_main)
                }
            }
        })
        mViewModel?.apiError?.observe(this, Observer {
            it?.let {
                showSnackBar(it)
            }
        })

        mViewModel?.isLoading?.observe(this, Observer {
            it?.let { showLoading(it) }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_otp_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getBundledArguments()
        clickListeners()

    }

    private fun getBundledArguments() {
        if (!arguments?.isEmpty!!) {
            phone_number = arguments?.getString(Constants.PHONE_NUMBER)
            startVerification()
        }
    }

    private fun startVerification() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_number!!, // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                activity!!, // Activity (for callback binding)
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        val code = phoneAuthCredential.smsCode
                        if (code != null) {

                            code_one_et.setText(code[0].toString())
                            code_two_et.setText(code[1].toString())
                            code_three_et.setText(code[2].toString())
                            code_fourth_et.setText(code[3].toString())
                            code_five_et.setText(code[4].toString())
                            code_six_et.setText(code[5].toString())

                            verifyVerificationCode(code)
                        }
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        showSnackBar("failed")
                    }

                    override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                        super.onCodeSent(p0, p1)
                        mVerificationId = p0;
                    }


                })
    }

    private fun initViews() {
        mAuth = FirebaseAuth.getInstance();
        code_one_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText
                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_two_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_three_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_fourth_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        code_five_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    next?.requestFocus()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        code_six_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val text = activity?.currentFocus as EditText

                if (text.length() > 0) {
                    val next = text.focusSearch(View.FOCUS_RIGHT) // or FOCUS_FORWARD
                    verifyOtp()
                } else {
                    val next = text.focusSearch(View.FOCUS_LEFT) // or FOCUS_FORWARD
                    next?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }

    private fun verifyVerificationCode(code: String) {
        showLoading(true)
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)

        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    private fun verifyOtp() {
        var code = code_one_et.text.toString() + code_two_et.text.toString() + code_three_et.text.toString() + code_fourth_et.text.toString() + code_five_et.text.toString() + code_six_et.text.toString()
        verifyVerificationCode(code);

    }

    private fun clickListeners() {

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)
                ?.addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        showLoading(false)
                        checkExistence()
                        Preferences?.prefs?.saveValue(Constants.IS_NUMBER_VERIFIED, true)
                        // replaceFragment(OtpVerifiedFragment(), true, R.id.container_main)
                    } else {
                        showLoading(false)
                        showSnackBar("Please Enter a valid OTP")
                    }
                }
    }

    private fun checkExistence() {
        mViewModel?.authenticate(phone_number!!)
    }
}