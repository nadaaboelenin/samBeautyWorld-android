package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.mainHome.Upcoming
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_appointments.view.*


class MyUpcomingAppointments(private var myDataset: ArrayList<Upcoming>?, private var activity: Context?) :
        RecyclerView.Adapter<MyUpcomingAppointments.MyViewHolder>() {

    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyUpcomingAppointments.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_appointments, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(myDataset!![position].icon).into(holder.itemView.ivSalonImage)
        holder.itemView.tvSalonNameItem.text = myDataset!![position].business_name
        holder.itemView.tvDateOfAppointment.text = myDataset!![position].date
        holder.itemView.tvAppointmentTime.text = myDataset!![position].time
        holder.itemView.tvTypeOfService.text = myDataset!![position].service_request
        holder.itemView.tvServicesBookedPrice.text = myDataset!![position].service_price + " AED"

        holder.itemView.tvAmountDuePrice.text = myDataset!![position].service_price + " AED"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}