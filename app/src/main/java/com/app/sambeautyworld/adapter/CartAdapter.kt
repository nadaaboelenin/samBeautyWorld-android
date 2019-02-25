package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AddRemoveListener
import com.app.sambeautyworld.callBack.OnItemClicked
import com.app.sambeautyworld.pojo.salonScreen.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_salon_products.view.*

class CartAdapter(private var myDataset: ArrayList<Product>?, private var activity: Context?,
                  private var onItemClicked: OnItemClicked?,
                  private var addRemoveListener: AddRemoveListener?
) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    class MyViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CartAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_products_cart, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.tvProductTitle.text = myDataset!![position].product_name
        holder.layout.tvProductPrice.text = myDataset!![position].price + " AED"
        Picasso.get().load(myDataset!![position].product_image).into(holder.layout.ivProductCircleView)
        holder.layout.tvProductsCount.text = myDataset!![position].count.toString()

        holder.layout.ivRemoveItems.setOnClickListener {
            addRemoveListener?.addRemove(position, position, 2, myDataset!![position].count - 1)
        }

        holder.layout.ivAddProducts.setOnClickListener {
            addRemoveListener?.addRemove(position, position, 2, myDataset!![position].count + 1)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size

}