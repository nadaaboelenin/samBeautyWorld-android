package com.app.sambeautyworld.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.mainHome.SpecialOffer
import com.app.sambeautyworld.ui.serviceSelectorTab.ActivitySalonStartPoint
import com.app.sambeautyworld.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_special_offers.view.*
import java.util.*

internal class SpecialOffersAdapter(var mContext: Context, private val mList: ArrayList<SpecialOffer>) : PagerAdapter() {
    private val mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return mList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as CardView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.item_special_offers, container, false)
        //        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //        imageView.setImageResource(Integer.parseInt(mList.get(position)));

        Picasso.get().load(mList[position].salon_logo)
                .into(itemView.ivBigImage)
        itemView.tvDiscountPrice.text = "All for " + mList[position].discount_price
        itemView.tvTypeOfServiceOffers.text = mList[position].salon_for
        itemView.tvSalonNameOffers.text = mList[position].salon_name

        itemView.btBookFromSpecialOffers.setOnClickListener {
            val intent = Intent(mContext, ActivitySalonStartPoint::class.java)
            intent.putExtra(Constants.FROM_SEARCH, "1")
            intent.putExtra(Constants.BUSINES_OWNER, mList!![position].ownder_id)
            mContext.startActivity(intent)
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}