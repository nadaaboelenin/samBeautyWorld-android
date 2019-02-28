package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.filteredsearch.Datum
import com.hmu.kotlin.callBack.OptionSelected
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_at_the_salon.view.*

class AtTheSalonAdapter(private var myDataset: MutableList<Datum>,
                        private var activity: Context?,
                        private var onItemClicked: OptionSelected?
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
            onItemClicked?.selectedItem(myDataset[position].owner_id)
        }

        Picasso.get().load(myDataset!![position].media).into(holder.layout.ivBigImageSalonAvailable)
        Picasso.get().load(myDataset!![position].business_logo).into(holder.layout.ivSalonLogo)
        holder.layout.tvDiscountPriceAtTheSalon.text = "Price Starting from " + myDataset!![position].price + " AED"
        holder.layout.tvTypeOfServiceOffers.text = myDataset!![position].business_name
        holder.layout.tvSalonNameOffers.text = myDataset!![position].city

//        if (myDataset!![position].service_for == "1") {
//            holder.layout.ivSalonAvailable.visibility = View.VISIBLE
//            holder.layout.ivHomeAvailable.visibility = View.INVISIBLE
//        }
//        if (myDataset!![position].service_at == "2") {
//            holder.layout.ivSalonAvailable.visibility = View.INVISIBLE
//            holder.layout.ivHomeAvailable.visibility = View.VISIBLE
//        }
//        if (myDataset!![position].service_at == "3") {
//            holder.layout.ivSalonAvailable.visibility = View.VISIBLE
//            holder.layout.ivHomeAvailable.visibility = View.VISIBLE
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}