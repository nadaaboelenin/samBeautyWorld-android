package com.app.sambeautyworld.adapter.agentscreen

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AgentListeners
import kotlinx.android.synthetic.main.item_dates.view.*

class AgentTimingAdapter(private var myDataset: ArrayList<String>?,
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

        holder.itemView.tvTimings.text = myDataset!![position]
        holder.itemView.tvTimings.setOnClickListener {
            listner?.onItemClick(position_service, position_agent, position)
            holder.itemView.tvTimings.setBackgroundColor(ContextCompat.getColor(activity1, R.color.backgroundColor))
            holder.itemView.tvTimings.setTextColor(ContextCompat.getColor(activity1, R.color.white))
        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}