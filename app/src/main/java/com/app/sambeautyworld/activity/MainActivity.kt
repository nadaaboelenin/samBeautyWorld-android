package com.app.sambeautyworld.activity

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseActivity
import com.app.sambeautyworld.ui.enableLocation.EnableLocationServices
import com.app.sambeautyworld.ui.mobileAuth.login.SignUpModel
import com.app.sambeautyworld.ui.splash.SplashFragment
import com.app.sambeautyworld.utils.Constants
import com.app.sambeautyworld.utils.saveValue
import com.app.sambeautyworld.utils.social.FacebookLoginManager
import com.app.sambeautyworld.utils.social.FacebookModel
import com.app.sambeautyworld.utils.social.SocialLoginManager
import com.facebook.internal.CallbackManagerImpl
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.iid.FirebaseInstanceId
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity(), SocialLoginManager.SocialLoginListener, GoogleApiClient.OnConnectionFailedListener {
    private val RC_SIGN_IN = 7
    private var mViewModel: SignUpModel? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private var newToken: String? = null
    var mFacebookLoginManager: FacebookLoginManager? = null

    override fun onGoogleLogin() {
        val intent: Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    fun initGooglePlus() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }


    override fun onFacbookLogin() {
        mFacebookLoginManager = FacebookLoginManager(this, this)
        mFacebookLoginManager?.login()
    }


    /*****************Facebook Login Callbacks*******************/
    override fun onLoginFailure(errorCode: Int) {
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginStart() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {

            if (mFacebookLoginManager != null) {
                mFacebookLoginManager?.onActivityResult(requestCode, resultCode, data)
            }
        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode === RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }

    }


    private fun handleSignInResult(result: GoogleSignInResult) {

        if (result.isSuccess) {
            val acct = result.signInAccount

            if (acct != null) {

            }
            val personPhotoUrl = acct?.photoUrl.toString()
            val model = FacebookModel()
            model.id = acct?.id
            model.firstName = acct?.displayName
            model.email = acct?.email
            model.avatarUrl = personPhotoUrl
            model.type = "1"
            EventBus.getDefault().post(model)
            hitSocialApi(model)
        } else {

        }
    }

    private fun hitSocialApi(model: FacebookModel) {
//        EventBus.getDefault().post(model)

        mViewModel?.registerUser(Preferences.prefs?.getString(Constants.PHONE_NUMBER, "0")!!,
                model.firstName, "", model.email, "", "1", model.type, "", newToken
        )
    }


    override fun onLoginSuccess(userData: FacebookModel?) {
        userData?.type = "2"
        EventBus.getDefault().post(userData)

        if (userData != null) {
            hitSocialApi(userData)
        }
    }


    override fun getID(): Int {
        return R.layout.activity_main
    }

    override fun iniView(savedInstanceState: Bundle?) {
        initGooglePlus()
        initViews()

    }


    private fun initViews() {
        mViewModel = ViewModelProviders.of(this)[SignUpModel::class.java]
        addFragment(SplashFragment(), true, R.id.container_main)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            newToken = instanceIdResult.token
        }
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer { it ->
            it?.let {
                if (it.status == 1) {
                    Preferences.prefs?.saveValue(Constants.IS_LOGGED_IN, true)
                    Preferences.prefs?.saveValue(Constants.ID, it.user_info!!.user_id)
                    replaceFragment(EnableLocationServices(), R.id.container_main)

                } else {

                }
            }
        })

        mViewModel?.apiError?.observe(this, Observer { it ->
            it?.let {

            }
        })

        mViewModel?.isLoading?.observe(this, Observer { it ->
            it?.let { showLoading(it) }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
