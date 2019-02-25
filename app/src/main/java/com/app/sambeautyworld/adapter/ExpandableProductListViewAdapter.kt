package com.app.sambeautyworld.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.sambeautyworld.R
import com.app.sambeautyworld.callBack.AddRemoveListener
import com.app.sambeautyworld.callBack.OnItemClickListener
import com.app.sambeautyworld.pojo.salonScreen.ProductsList
import com.squareup.picasso.Picasso

class ExpandableProductListViewAdapter(private val context: Context, private val listDataGroup: List<ProductsList>?,
                                       private var onItemClicked: OnItemClickListener?,
                                       private var addRemoveListener: AddRemoveListener?)
    : BaseExpandableListAdapter() {
    private var i: Int = 0

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return this.listDataGroup!![groupPosition].products!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView


        val childText = listDataGroup!![groupPosition].products!![childPosition].product_name.toString()

        i = listDataGroup[groupPosition].products!![childPosition].count

        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_salon_products, null)
        }

        val textViewChild = convertView!!
                .findViewById<TextView>(R.id.tvProductTitle)
        textViewChild.text = childText

        //    convertView.findViewById<TextView>(R.id.tvSubServiceTime).text =listDataGroup[groupPosition].products!![childPosition].product_name.toString()+ " min"
        convertView.findViewById<TextView>(R.id.tvProductPrice).text =
                listDataGroup[groupPosition].products!![childPosition].price.toString() + " AED"

        convertView.findViewById<TextView>(R.id.tvProductMore).setOnClickListener {
            onItemClicked?.onItemClick(groupPosition, childPosition, 2)
        }

        convertView.findViewById<TextView>(R.id.tvProductsCount).text = i.toString()

        convertView.findViewById<ImageView>(R.id.ivAddProducts).setOnClickListener {
            addRemoveListener?.addRemove(groupPosition, childPosition, 2, listDataGroup[groupPosition].products!![childPosition].count + 1)
        }

        convertView.findViewById<ImageView>(R.id.ivRemoveItems).setOnClickListener {
            if (listDataGroup[groupPosition].products!![childPosition].count > 0) {
                addRemoveListener?.addRemove(groupPosition, childPosition, 2, listDataGroup[groupPosition].products!![childPosition].count - 1)
            }
        }

        Picasso.get().load(listDataGroup[groupPosition].products!![childPosition].product_image).into(convertView.findViewById<ImageView>(R.id.ivProductCircleView))

        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.listDataGroup!![groupPosition].products!!.size
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