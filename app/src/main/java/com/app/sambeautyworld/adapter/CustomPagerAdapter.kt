package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R

internal class CustomPagerAdapter(mContext: Context, private val mList: ArrayList<String>) : PagerAdapter() {
    private val mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return mList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.item_single_image, container, false)
        //        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //        imageView.setImageResource(Integer.parseInt(mList.get(position)));
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}