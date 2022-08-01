package com.pharma.blooddonate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(var mynotifications: ArrayList<NotificationModel>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notifications, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.disc.text = "" + mynotifications[position].disc
        holder.image.setImageResource(mynotifications[position].image)
    }

    override fun getItemCount(): Int {
        return mynotifications.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var disc: TextView
        var image: ImageView

        init {
            disc = itemView.findViewById(R.id.disc)
            image = itemView.findViewById(R.id.image)
        }
    }
}
