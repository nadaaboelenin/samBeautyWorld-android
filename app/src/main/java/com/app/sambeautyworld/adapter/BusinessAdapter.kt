package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.businessType.AllBusinessList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_right.view.*

class BusinessAdapter(private var myDataset: ArrayList<AllBusinessList>?,
                      private var activity: Context?,
                      private var onItemClicked: OnItemClicked?
) : RecyclerView.Adapter<BusinessAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BusinessAdapter.MyViewHolder {


        val v: View
        if (viewType == 0) {
            v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_right, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_left, parent, false)
        }
        return MyViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvSalonNameBusiness.text = myDataset!![position].business_type
        Picasso.get().load(myDataset!![position].business_image).into(holder.itemView.ivBusinessImage)
        holder.itemView.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return 0
        } else {
            return 1
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}