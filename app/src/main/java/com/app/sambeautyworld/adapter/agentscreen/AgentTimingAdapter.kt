package com.app.sambeautyworld.adapter.agentscreen

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AgentListeners
import com.app.sambeautyworld.pojo.agents.Timingssss
import kotlinx.android.synthetic.main.item_dates.view.*

class AgentTimingAdapter(private var myDataset: ArrayList<Timingssss>,
                         private var listner: AgentListeners?,
                         var position_service: Int,
                         var position_agent: Int,
                         var activity1: Context
) : RecyclerView.Adapter<AgentTimingAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {


        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dates, parent, false)


        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            onItemClicked?.onItemClick(position)
//        }

        holder.itemView.tvTimings.text = myDataset[position].time
        if (myDataset[position].check!!) {
            holder.itemView.tvTimings.setBackgroundColor(ContextCompat.getColor(activity1, R.color.backgroundColor))
            holder.itemView.tvTimings.setTextColor(ContextCompat.getColor(activity1, R.color.white))
        } else {
            holder.itemView.tvTimings.setBackgroundColor(ContextCompat.getColor(activity1, R.color.white))
            holder.itemView.tvTimings.setTextColor(ContextCompat.getColor(activity1, android.R.color.black))
        }


        holder.itemView.tvTimings.setOnClickListener {
            for (i in 0 until myDataset.size) {
                myDataset[i].check = false
            }
            listner?.onItemClick(position_service, position_agent, position)
            myDataset[position].check = !myDataset[position].check!!
            notifyDataSetChanged()
        }


    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}