package com.app.sambeautyworld.ui.chatview

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.sambeautyworld.R
import com.app.sambeautyworld.adapter.ChatSupportAdapter
import com.app.sambeautyworld.adapter.MyAppointments
import kotlinx.android.synthetic.main.fragment_chat_support.*
import kotlinx.android.synthetic.main.fragment_my_appointments.*


class ChatSupportFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat_support, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        rv_chathistory.adapter= ChatSupportAdapter(this!!.context!!)
        rv_chathistory.layoutManager = LinearLayoutManager(context,1,false)
    }


}
