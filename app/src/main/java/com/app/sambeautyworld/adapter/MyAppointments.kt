package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.dummyData.DummyAppointmentsPojo


class MyAppointments(private var myDataset: ArrayList<DummyAppointmentsPojo>?, private var activity: Context?) :
        RecyclerView.Adapter<MyAppointments.MyViewHolder>() {

    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAppointments.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_appointments, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.layout.tvBookmarksName.text = myDataset!![position].names
//        Picasso.get().load(myDataset!![position].images).into(holder.layout.ivBookmarks)


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}