package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_at_the_salon.view.*

class MyAdapter(private var myDataset: ArrayList<com.app.sambeautyworld.pojo.getbookmark.BookMark>?, private var activity: Context?,
                private var onItemClicked: OnItemClicked?
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {


        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_at_the_salon, parent, false)

        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.itemView.tvSalonNameBusiness.text = myDataset!![position].business_type
//        Picasso.get().load(myDataset!![position].business_image).into(holder.itemView.ivBusinessImage)
//        holder.itemView.setOnClickListener {
//            onItemClicked?.onItemClick(position)
//        }


        holder.itemView.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }

        Picasso.get().load(myDataset!![position].media).into(holder.itemView.ivBigImageSalonAvailable)
        Picasso.get().load(myDataset!![position].bookmark_logo).into(holder.itemView.ivSalonLogo)
        holder.itemView.tvDiscountPriceAtTheSalon.text = "Price Starting from " + myDataset!![position].price + " AED"
        holder.itemView.tvTypeOfServiceOffers.text = myDataset!![position].bookmark_name
        holder.itemView.tvSalonNameOffers.text = myDataset!![position].location

        if (myDataset!![position].atsalon_service == 1) {
            holder.itemView.ivSalonAvailable.visibility = View.VISIBLE
            holder.itemView.ivHomeAvailable.visibility = View.INVISIBLE
        }
        if (myDataset!![position].home_service == 2) {
            holder.itemView.ivSalonAvailable.visibility = View.INVISIBLE
            holder.itemView.ivHomeAvailable.visibility = View.VISIBLE
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}