package com.app.sambeautyworld.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.mainHome.SpecialOffer
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_special_offers.view.*

class SeeAllOffersAdapter(private var myDataset: ArrayList<SpecialOffer>?, private var activity: Context?,
                          private var onItemClicked: OnItemClicked?
) :
        RecyclerView.Adapter<SeeAllOffersAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SeeAllOffersAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_special_offers, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(myDataset!![position].salon_logo)
                .into(holder.layout.ivBigImage)
        holder.layout.tvDiscountPrice.text = "All for " + myDataset!![position].discount_price
        holder.layout.tvTypeOfServiceOffers.text = myDataset!![position].salon_for
        holder.layout.tvSalonNameOffers.text = myDataset!![position].salon_name

        holder.layout.btBookFromSpecialOffers.setOnClickListener {
            val intent = Intent(activity!!, ActivitySalonStartPoint::class.java)
            intent.putExtra(Constants.FROM_SEARCH, "1")
            intent.putExtra(Constants.BUSINES_OWNER, myDataset!![position].ownder_id)
            activity!!.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}