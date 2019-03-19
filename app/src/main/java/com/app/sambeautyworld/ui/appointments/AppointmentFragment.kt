package com.app.sambeautyworld.ui.appointments

import Preferences
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyPastAppointments
import com.app.sambeautyworld.adapter.MyUpcomingAppointments
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.dummyData.DummyAppointmentsPojo
import com.app.sambeautyworld.pojo.mainHome.Past
import com.app.sambeautyworld.pojo.mainHome.Upcoming
import com.app.sambeautyworld.ui.home.HomeActivity
import com.app.sambeautyworld.utils.Constants
import kotlinx.android.synthetic.main.fragment_my_appointments.*

/**
 * Created by ${Shubham} on 1/1/2019.
 */
class AppointmentFragment : BaseFragment(), OnItemClicked {
    override fun onItemClick(position: Int) {
        mViewModel?.cancelService(position.toString(), Preferences.prefs?.getString(Constants.ID, "1")!!)
    }


    private var dummyAppointmentList: ArrayList<DummyAppointmentsPojo>? = ArrayList()
    private var upcoming: ArrayList<Upcoming>? = ArrayList()
    private var past: ArrayList<Past>? = ArrayList()
    private var mViewModel: AppointmentModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        getBundledData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[AppointmentModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    startActivity(Intent(activity!!, HomeActivity::class.java))
                } else
                    showMessage(it.message)
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


    private fun getBundledData() {
        if (!arguments!!.isEmpty) {
            past = arguments?.getParcelableArrayList("past")
            upcoming = arguments?.getParcelableArrayList("upcoming")
            setUpData()
        }
    }

    private fun clickListeners() {


        tvGoBackYourAppointments.setOnClickListener {
            goBack()
        }

        tvUpcomingAppointment.setOnClickListener {
            changeColor(tvUpcomingAppointment, view_upcoming)
            rvAppointMentHistory.adapter = MyUpcomingAppointments(upcoming, context, this)
            rvAppointMentHistory.layoutManager = LinearLayoutManager(context, 1, false)
        }

        tvPastAppointments.setOnClickListener {
            changeColor(tvPastAppointments, view_past)
            rvAppointMentHistory.adapter = MyPastAppointments(past, context, this)
            rvAppointMentHistory.layoutManager = LinearLayoutManager(context, 1, false)
        }


    }

    private fun changeColor(textView: TextView?, view: View) {
        tvUpcomingAppointment.setTextColor(ContextCompat.getColor(this.context!!, android.R.color.darker_gray))
        tvPastAppointments.setTextColor(ContextCompat.getColor(this.context!!, android.R.color.darker_gray))
        view_past.setBackgroundColor(ContextCompat.getColor(this.context!!, android.R.color.white))
        view_upcoming.setBackgroundColor(ContextCompat.getColor(this.context!!, android.R.color.white))
        textView?.setTextColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.backgroundColor))
    }

    private fun setUpData() {
        rvAppointMentHistory.adapter = MyUpcomingAppointments(upcoming, context, this)
        rvAppointMentHistory.layoutManager = LinearLayoutManager(context, 1, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_appointments, container, false)
    }
}