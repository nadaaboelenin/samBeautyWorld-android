package com.app.sambeautyworld.adapter.agentscreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import kotlinx.android.synthetic.main.item_dates.view.*

class AgentTimingAdapter(private var myDataset: ArrayList<String>?,
                         private var activity: Context?,
                         private var context: Context?,
                         private var listner: OnItemClicked?
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
            listner?.onItemClick(position)

        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}