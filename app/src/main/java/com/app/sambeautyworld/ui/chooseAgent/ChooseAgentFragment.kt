package com.app.sambeautyworld.ui.chooseAgent

import Preferences
import android.annotation.TargetApi
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.agentscreen.ServicesOfAgentAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.AgentListeners
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.agents.ServiceDatum
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest
import com.app.sambeautyworld.pojo.requestbooking.Datum
import com.app.sambeautyworld.pojo.requestbooking.Request
import com.app.sambeautyworld.pojo.salonScreen.SubService
import com.app.sambeautyworld.ui.serviceSelectorTab.paymentFragment.PaymentFragment
import com.app.sambeautyworld.utils.Constants
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import kotlinx.android.synthetic.main.fragment_choose_agent.*
import java.util.*


class ChooseAgentFragment : BaseFragment(), AgentListeners {
    var data: ArrayList<Datum>? = ArrayList()
    var salon_id: String? = ""
    var serviceData: ArrayList<ServiceDatum>? = ArrayList()
    lateinit var request: Request
    var days = "00"
    var months = "00"
    var years = "0000"


    override fun onItemClick(position_service: Int, position_agent: Int, position_time: Int) {
        btContinue.isEnabled = true
        data!!.add(Datum(serviceData!![position_service].sub_service_name, serviceData!![position_service].sub_service_price, "1",
                serviceData!![position_service].agents[position_agent].id, "$years-$months-$days", serviceData!![position_service].agents[position_agent].timings[position_time]
                , salon_id, "50"))

        for (i in 0..serviceData!![position_service].agents.size) {

        }

    }


    private var subServices: ArrayList<SubService> = ArrayList()
    private var id: String? = null
    private var services: ArrayList<GetAgentsRequest.Sub_services> = ArrayList()
    private var mViewModel: GetAgentsModel? = null
    private var getAgentRequest: GetAgentsRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[GetAgentsModel::class.java]
        attachObservers()
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    setUpData(it)
                }
            }
        })

        mViewModel?.response_book?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    showMessage(it.message)
                    replaceFragment(PaymentFragment(), false, R.id.container_home_salon)
                }
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

    private fun setUpData(agentsPojo: AgentsPojo) {
        serviceData!!.clear()
        serviceData = agentsPojo.serviceData as ArrayList<ServiceDatum>
        rvAgentsWholeInformation.adapter = ServicesOfAgentAdapter((agentsPojo.serviceData as ArrayList<ServiceDatum>?)!!, activity!!, this)
        rvAgentsWholeInformation.layoutManager = LinearLayoutManager(activity!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundledArguments()
        clickListeners()
        setupData()
    }

    private fun setupData() {
        val c = Calendar.getInstance()
        years = c.get(Calendar.YEAR).toString()
        months = c.get(Calendar.MONTH).toString()
        days = c.get(Calendar.DAY_OF_MONTH).toString()



        collapsibleCalendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onDaySelect() {
                val day = collapsibleCalendar.selectedDay
                Log.i(javaClass.name, "Selected Day: "
                        + day.year + "/" + (day.month + 1) + "/" + day.day)

                if (day.day < 10) {
                    days = "0" + day.day
                } else {
                    days = "" + day.day
                }

                if (day.month < 10) {
                    months = "0" + (day.month + 1)
                } else {
                    months = "" + (day.month + 1)
                }

                var dayOfWeek = "Sunday"

                val calendar = GregorianCalendar(day.year, day.month, day.day) // Note that Month value is 0-based. e.g., 0 for January.
                val reslut = calendar.get(Calendar.DAY_OF_WEEK)
                when (reslut) {
                    Calendar.MONDAY -> {
                        dayOfWeek = "Monday"
                    }
                    Calendar.TUESDAY -> {
                        dayOfWeek = "Tuesday"
                    }
                    Calendar.WEDNESDAY -> {
                        dayOfWeek = "Wednesday"
                    }
                    Calendar.THURSDAY -> {
                        dayOfWeek = "Thursday"
                    }
                    Calendar.FRIDAY -> {
                        dayOfWeek = "Friday"
                    }
                    Calendar.SATURDAY -> {
                        dayOfWeek = "Saturday"
                    }
                    Calendar.SUNDAY -> {
                        dayOfWeek = "Sunday"
                    }
                }

                getAgentRequest = GetAgentsRequest(id, services, dayOfWeek)
                hitApi()

            }

            override fun onItemClick(view: View) {

            }

            override fun onDataUpdate() {

            }

            override fun onMonthChange() {

            }

            override fun onWeekChange(i: Int) {

            }
        })
    }

    private fun clickListeners() {

        btContinue.setOnClickListener {
            if (data?.size != 0)
                request = Request(id, Preferences.prefs!!.getString(Constants.ID, "0"), data)
            hitBookingApi()
        }


    }

    private fun hitBookingApi() {
        if (request != null) {
            mViewModel?.bookService(request)
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun getBundledArguments() {
        btContinue.isEnabled = false
        subServices = BaseFragment.subServices

        if (!arguments!!.isEmpty) {
            id = arguments!!.getString("id")
            salon_id = arguments!!.getString("salonId")
        }

        val size: Int = subServices.size

        subServices.forEachIndexed { index, _ ->
            services.add(GetAgentsRequest.Sub_services(subServices[index].subservice_id))
        }
        val c = java.util.Calendar.getInstance()
        val sd = java.text.SimpleDateFormat("EEEE")
        val dayofweek = sd.format(c.time)
        getAgentRequest = GetAgentsRequest(id, services, dayofweek)
        hitApi()
    }

    private fun hitApi() {
        mViewModel?.getAgents(getAgentRequest!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.fragment_choose_agent, container, false)
    }


}