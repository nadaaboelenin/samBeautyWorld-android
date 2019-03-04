package com.app.sambeautyworld.ui.chooseAgent

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.agentscreen.ServicesOfAgentAdapter
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.agents.ServiceDatum
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest
import com.app.sambeautyworld.pojo.salonScreen.SubService
import kotlinx.android.synthetic.main.fragment_choose_agent.*

class ChooseAgentFragment : BaseFragment(), OnItemClicked {

    private var listener: OnItemClicked? = null
    override fun onItemClick(position: Int) {
        showMessage("Choose=$position")
    }

    private var subServices: ArrayList<SubService> = ArrayList()
    private var id: String? = null
    private var services: ArrayList<GetAgentsRequest.Services> = ArrayList()
    private var mViewModel: GetAgentsModel? = null
    private var getAgentRequest: GetAgentsRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this)[GetAgentsModel::class.java]
        attachObservers()
        listener = this
    }

    private fun attachObservers() {
        mViewModel?.response?.observe(this, Observer {
            it?.let {
                if (it.status == 1) {
                    setUpData(it)
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

    private fun setUpData(agentsPojo: AgentsPojo) {
        rvAgentsWholeInformation.adapter = ServicesOfAgentAdapter((agentsPojo.serviceData as ArrayList<ServiceDatum>?)!!, activity!!, listener, agentsPojo.timings as ArrayList<String>)
        rvAgentsWholeInformation.layoutManager = LinearLayoutManager(activity!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundledArguments()
    }

    private fun getBundledArguments() {

        subServices = BaseFragment.subServices

        if (!arguments!!.isEmpty) {
            id = arguments!!.getString("id")
        }
        val size: Int = subServices.size

        subServices.forEachIndexed { index, _ ->
            services.add(GetAgentsRequest.Services(subServices[index].subservice_id))
        }
        getAgentRequest = GetAgentsRequest(id, services)
        hitApi()

    }

    private fun hitApi() {
        mViewModel?.getAgents(getAgentRequest!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_agent, container, false)
    }
}