package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import kotlinx.android.synthetic.main.item_special_offers.view.*

class SeeAllOffersAdapter(private var myDataset: ArrayList<String>?, private var activity: Context?,
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
//        holder.layout.tvBookmarksName.text = myDataset!![position].names
//        Picasso.get().load(myDataset!![position].images).into(holder.layout.ivBookmarks)

        holder.layout.btBookFromSpecialOffers.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}