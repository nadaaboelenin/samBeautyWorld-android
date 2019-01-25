package com.app.sambeautyworld.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R


class ChatSupportAdapter(var  mContext : Context) : RecyclerView.Adapter<ChatSupportAdapter.Myviewholder>() {
    var sender: Int = 0
    var recicver: Int = 1

    class Myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ChatSupportAdapter.Myviewholder {
        var v: View
        if (p1 == recicver) {
            v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_receivermessage, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sendmessage, parent, false)
        }
        return Myviewholder(v)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return sender
        } else {
            return recicver
        }

    }

    override fun onBindViewHolder(p0: ChatSupportAdapter.Myviewholder, p1: Int) {

    }


}