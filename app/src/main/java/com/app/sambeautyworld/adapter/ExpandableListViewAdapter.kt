package com.app.sambeautyworld.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.app.sambeautyworld.R
import com.app.sambeautyworld.pojo.salonScreen.ServicesList

class ExpandableListViewAdapter(private val context: Context, private val listDataGroup: List<ServicesList>?) : BaseExpandableListAdapter() {


    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return this.listDataGroup!![groupPosition]!!.subServices!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val childText = listDataGroup!![groupPosition].subServices!![childPosition].subservice_name.toString()

        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_sub_services, null)
        }

        val textViewChild = convertView!!
                .findViewById<TextView>(R.id.tvSubServiceTitle)
        textViewChild.text = childText

        convertView.findViewById<TextView>(R.id.tvSubServiceTime).text =listDataGroup[groupPosition].subServices!![childPosition].service_time.toString()+ " min"
        convertView.findViewById<TextView>(R.id.tvSubServicePrice).text =listDataGroup[groupPosition].subServices!![childPosition].service_price.toString() + " AED"

        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        return this.listDataGroup!![groupPosition].subServices!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.listDataGroup!![groupPosition]
    }

    override fun getGroupCount(): Int {
        return this.listDataGroup!!.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val headerTitle = listDataGroup!![groupPosition].service_name
        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_main_services, null)
        }

        val textViewGroup = convertView!!
                .findViewById<TextView>(R.id.tvMainServiceListText)
        textViewGroup.setTypeface(null, Typeface.BOLD)
        textViewGroup.text = headerTitle

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}