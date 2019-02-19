package com.app.sambeautyworld.adapter.agentscreen

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.agents.Agent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_agent_information.view.*

class AgentNameAdapter(private var myDataset: ArrayList<Agent>?, private var activity: Context?,
                       private var onItemClicked: ServicesOfAgentAdapter,
                       private var timings: ArrayList<String>
) : RecyclerView.Adapter<AgentNameAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var flagger: Boolean? = false

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {


        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_agent_information, parent, false)

        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            onItemClicked?.onItemClick(position)
//        }

        holder.itemView.tvAgentName.text = myDataset!![position].agent_name
        holder.itemView.tvAgentLocation.text = myDataset!![position].nationality
        Picasso.get().load(myDataset!![position].agent_image).into(holder.itemView.ivAgentImage)
        holder.itemView.rvAgentTimigngs.adapter = AgentTimingAdapter(timings, activity!!, this)
        holder.itemView.rvAgentTimigngs.layoutManager = GridLayoutManager(activity, 5)


        holder.itemView.ivShowAgentTiming.setOnClickListener {
            if (!flagger!!) {
                flagger = !flagger!!
                holder.itemView.rvAgentTimigngs.visibility = View.VISIBLE
            } else {
                flagger = !flagger!!
                holder.itemView.rvAgentTimigngs.visibility = View.GONE

            }


        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}