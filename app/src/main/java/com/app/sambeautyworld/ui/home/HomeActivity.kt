package com.app.sambeautyworld.ui.home

import Preferences
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseActivity
import com.app.sambeautyworld.ui.businessType.BusinessTypeFragment
import com.app.sambeautyworld.ui.chooseAgent.ChooseAgentFragment
import com.app.sambeautyworld.ui.sideMenuOpions.favourites.FavouritesFragment
import com.app.sambeautyworld.ui.sideMenuOpions.listYourBusiness.ListYourBusinessFragment
import com.app.sambeautyworld.ui.sideMenuOpions.myAccount.MyAccountFragment
import com.app.sambeautyworld.ui.sideMenuOpions.searchSalon.SearchSalonFragment
import com.app.sambeautyworld.ui.sideMenuOpions.sendFeedback.SendFeedbackFragment
import com.app.sambeautyworld.ui.sideMenuOpions.wallet.WalletFragment
import com.app.sambeautyworld.utils.saveValue
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.drawer_items.*

class HomeActivity : BaseActivity() {

    override fun getID(): Int {
        return R.layout.activity_home
    }

    override fun iniView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, android.R.color.black)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        llHome.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.VISIBLE
            addFragment(BusinessTypeFragment(), true, R.id.container_home)
        }

        btListYourBusiness.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(ListYourBusinessFragment(), true, R.id.container_home)
        }

        llSendFeedback.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(SendFeedbackFragment(), true, R.id.container_home)
        }

        llMyAccount.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(MyAccountFragment(), true, R.id.container_home)
        }

        llFavourite.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(FavouritesFragment(), true, R.id.container_home)
        }

        llSearch.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(SearchSalonFragment(), true, R.id.container_home)
        }

        llWallet.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(WalletFragment(), true, R.id.container_home)
        }

        llGender.setOnClickListener {

            MaterialStyledDialog.Builder(this)
                    .setTitle("Select Your Gender!")
                    .setHeaderColor(R.color.backgroundColor)
                    .setIcon(R.mipmap.logo)
                    .setDescription("The App features will be filtered on the selected option.")
                    .setPositiveText("Man").setNegativeText("Woman").onPositive { dialog, which ->
                        tvGender.text = "Man"
                        Preferences.prefs?.saveValue("Gender", "Man")
                        drawer_layout.closeDrawer(Gravity.START)
                    }
                    .onNegative { dialog, which ->
                        tvGender.text = "Woman"
                        Preferences.prefs?.saveValue("Gender", "Woman")
                        drawer_layout.closeDrawer(Gravity.START)
                    }
                    .build()
                    .show()

        }

        llChangeTheLanguage.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toolbar.visibility = View.GONE
            addFragment(ChooseAgentFragment(), true, R.id.container_home)
        }
    }


    private fun setUpData() {
        tvGender.text = Preferences?.prefs?.getString("Gender", "Man")
        addFragment(BusinessTypeFragment(), true, R.id.container_home)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            toolbar.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                drawer_layout.closeDrawer(GravityCompat.START)
                toolbar.visibility = View.GONE
                addFragment(SearchSalonFragment(), true, R.id.container_home)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
