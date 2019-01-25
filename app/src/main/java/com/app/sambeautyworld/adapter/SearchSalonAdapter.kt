package com.app.sambeautyworld.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.dummyData.DummySalonList
import com.app.sambeautyworld.pojo.searchsallonpojo.AllSalonList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_salon_list.view.*

/**
 * Created by ${Shubham} on 12/28/2018.
 */
class SearchSalonAdapter(private var myDataset: ArrayList<AllSalonList>?) : RecyclerView.Adapter<SearchSalonAdapter.MyViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SearchSalonAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_salon_list, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.tvSalonName.text = myDataset!![position].business_name
       // holder.layout.tvSalonType.text  = myDataset!![position].
        if(!myDataset!![position].business_logo.equals("")){
            Picasso.get().load(myDataset!![position].business_logo!!).placeholder(R.mipmap.logo).into(holder.layout.ivSalonImage)
        }
        else{
            Picasso.get().load(R.mipmap.logo).placeholder(R.mipmap.logo).into(holder.layout.ivSalonImage)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size


    fun filterList(filterdNames:  ArrayList<AllSalonList>?) {
        myDataset=filterdNames
        notifyDataSetChanged()
    }
}