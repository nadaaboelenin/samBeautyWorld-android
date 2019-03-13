package com.app.sambeautyworld.adapter.agentscreen

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AgentListeners
import com.app.sambeautyworld.pojo.agents.Agent
import com.app.sambeautyworld.pojo.agents.ServiceDatum
import kotlinx.android.synthetic.main.item_services_choose_agents.view.*

class ServicesOfAgentAdapter(private var myDataset: ArrayList<ServiceDatum>?, private var activity: Context?,
                             private var listner: AgentListeners?
) : RecyclerView.Adapter<ServicesOfAgentAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_services_choose_agents, parent, false)

        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.tvServiceName.text = myDataset!![position].sub_service_name
        holder.itemView.rvAgentsInformation.adapter = AgentNameAdapter(myDataset!![position].agents as ArrayList<Agent>?, this.activity!!, listner, position, activity!!)
        holder.itemView.rvAgentsInformation.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)


    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}