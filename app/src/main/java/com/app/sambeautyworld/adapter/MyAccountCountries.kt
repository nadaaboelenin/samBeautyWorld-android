package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.accountPojo.Salon
import kotlinx.android.synthetic.main.item_selected_salon_account.view.*

class MyAccountCountries(private var myDataset: ArrayList<Salon>?, private var activity: Context?,
                         private var onItemClicked: OnItemClicked?
) :
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
        holder.layout.tvCountryName.text = myDataset!![position].country_name
        holder.layout.tvNumberOfSalons.text = myDataset!![position].total_salons + " Salons"

        if (myDataset!![position].selected!!) {
            holder.layout.tvSelectedSalon.visibility = View.VISIBLE
            holder.layout.setCardBackgroundColor(ContextCompat.getColor(activity!!,R.color.backgroundColor))
            holder.layout.tvCountryName.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
            holder.layout.tvNumberOfSalons.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        } else {
            holder.layout.tvSelectedSalon.visibility = View.GONE
            holder.layout.setCardBackgroundColor(ContextCompat.getColor(activity!!, R.color.white))
            holder.layout.tvCountryName.setTextColor(ContextCompat.getColor(activity!!, R.color.backgroundColor))
            holder.layout.tvNumberOfSalons.setTextColor(ContextCompat.getColor(activity!!, android.R.color.darker_gray))
        }

        holder.layout.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}