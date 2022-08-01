package com.pharma.blooddonate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NotificationPage : Fragment() {
    var allnotifications: RecyclerView? = null
    var mynotifications: ArrayList<NotificationModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.notificationpage, container, false)
        allnotifications = view.findViewById(R.id.allnotifications)
        allnotifications?.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        mynotifications = ArrayList<NotificationModel>()
        val notificationModel =
            NotificationModel("John posted about\nrequirement", R.drawable.person1)
        mynotifications!!.add(notificationModel)
        val notificationModel1 =
            NotificationModel("John posted about\nrequirement", R.drawable.person1)
        mynotifications!!.add(notificationModel1)
        val notificationModel2 =
            NotificationModel("John posted about\nrequirement", R.drawable.person1)
        mynotifications!!.add(notificationModel2)
        allnotifications?.setAdapter(NotificationAdapter(mynotifications!!))
        return view
    }
}
