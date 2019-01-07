package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.dummyData.NoOfSalonsPojo
import kotlinx.android.synthetic.main.item_selected_salon_account.view.*

class MyAccountCountries(private var myDataset: ArrayList<NoOfSalonsPojo>?, private var activity: Context?) :
        RecyclerView.Adapter<MyAccountCountries.MyViewHolder>() {

    class MyViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAccountCountries.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_selected_salon_account, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.tvCountryName.text = myDataset!![position].country
        holder.layout.tvNumberOfSalons.text = myDataset!![position].total_Salons

        if (myDataset!![position].selected!!) {
            holder.layout.tvSelectedSalon.visibility = View.VISIBLE
            holder.layout.setCardBackgroundColor(ContextCompat.getColor(activity!!,R.color.backgroundColor))
            holder.layout.tvCountryName.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
            holder.layout.tvNumberOfSalons.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
//            holder.layout.cardElevation= R.dimen._12sdp.toFloat()
//            holder.layout.radius = R.dimen._8sdp.toFloat()
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}