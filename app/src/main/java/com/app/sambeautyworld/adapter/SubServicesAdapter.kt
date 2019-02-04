package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AddRemoveListener
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.salonScreen.SubService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_sub_services.view.*

class SubServicesAdapter(private var myDataset: ArrayList<SubService>?, private var activity: Context?,
                         private var onItemClicked: OnItemClicked?,
                         private var addRemoveListener: AddRemoveListener?
) :
        RecyclerView.Adapter<SubServicesAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SubServicesAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_sub_servcies_cart, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.tvSubServiceTitle.text = myDataset!![position].subservice_name
        holder.layout.tvSubServiceTime.text = myDataset!![position].service_time
        holder.layout.tvSubServicePrice.text = myDataset!![position].service_price + " AED"
        Picasso.get().load(R.mipmap.remove_from_cart).into(holder.layout.ivAddToCart)

        holder.layout.ivAddToCart.setOnClickListener {
            addRemoveListener?.addRemove(position, position, 1, 1)
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}