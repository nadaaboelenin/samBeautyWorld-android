package com.app.sambeautyworld.ui.appointments

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.MyAppointments
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.dummyData.DummyAppointmentsPojo
import kotlinx.android.synthetic.main.fragment_my_appointments.*

/**
 * Created by ${Shubham} on 1/1/2019.
 */
class AppointmentFragment : BaseFragment() {
    private var dummyAppointmentList: ArrayList<DummyAppointmentsPojo>? = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        tvGoBackYourAppointments.setOnClickListener {
            goBack()
        }


        tvUpcomingAppointment.setOnClickListener {
            changeColor(tvUpcomingAppointment,view_upcoming)
        }

        tvPastAppointments.setOnClickListener {
            changeColor(tvPastAppointments,view_past)
        }


    }

    private fun changeColor(textView: TextView?, view: View) {
        tvUpcomingAppointment.setTextColor(ContextCompat.getColor(this.context!!,android.R.color.darker_gray))
        tvPastAppointments.setTextColor(ContextCompat.getColor(this.context!!,android.R.color.darker_gray))
        view_past.setBackgroundColor(ContextCompat.getColor(this.context!!,android.R.color.white))
        view_upcoming.setBackgroundColor(ContextCompat.getColor(this.context!!,android.R.color.white))

        textView?.setTextColor(ContextCompat.getColor(context!!,R.color.backgroundColor))
        view.setBackgroundColor(ContextCompat.getColor(context!!,R.color.backgroundColor))
    }

    private fun setUpData() {
        dummyAppointmentList?.add(DummyAppointmentsPojo(R.mipmap.salon, "As You Wish",
                "Sat 10 November 2018", "5:15 PM - 5:45 PM", "Back Massage",
                "160 AED", "0 AED", "160 AED", "9805191641", "30.7333", "76.7794"
        ))

        rvAppointMentHistory.adapter=MyAppointments(dummyAppointmentList,context)
        rvAppointMentHistory.layoutManager = LinearLayoutManager(context,1,false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_appointments, container, false)
    }
}