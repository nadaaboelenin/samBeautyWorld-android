package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked

class AtTheSalonAdapter(private var myDataset: ArrayList<String>?, private var activity: Context?,
                        private var onItemClicked: OnItemClicked?
) :
        RecyclerView.Adapter<AtTheSalonAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AtTheSalonAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_at_the_salon, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.layout.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}