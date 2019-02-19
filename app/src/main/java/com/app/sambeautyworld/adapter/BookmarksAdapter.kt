package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.mainHome.BookMark
import com.hmu.kotlin.callBack.ItemSelectedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_bookmarks.view.*

class BookmarksAdapter(private var myDataset: ArrayList<BookMark>?, private var activity: Context?,
                       private var onItemClicked: ItemSelectedListener?) :
        RecyclerView.Adapter<BookmarksAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BookmarksAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bookmarks, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.tvBookmarksName.text = myDataset!![position].bookmark_name
        Picasso.get().load(myDataset!![position].bookmark_logo).into(holder.layout.ivBookmarks)

        holder.layout.setOnClickListener {
            onItemClicked?.selectedItem(position, position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}