package com.app.sambeautyworld.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.dummyData.DummyFeaturedServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_featured_services.view.*

/**
 * Created by ${Shubham} on 12/28/2018.
 */
class FeaturedServicesAdapter(private val myDataset: ArrayList<DummyFeaturedServices>? , private var onItemClicked: OnItemClicked? ) :
        RecyclerView.Adapter<FeaturedServicesAdapter.MyViewHolder>()
{

    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FeaturedServicesAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_featured_services, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.layout.tvFeaturedServiceName.text = myDataset!![position].text
        Picasso.get().load(myDataset[position].image!!).into(holder.layout.ivServiceImage)

        holder.layout.setOnClickListener {
            onItemClicked?.onItemClick(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size
}