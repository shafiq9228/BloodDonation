package com.pharma.blooddonate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException


class PostListAdapter(var mypostlist: ArrayList<PostListModel>,var activity: Activity) :
    RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.postlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = "" + mypostlist[position].title
        holder.name.text = "" + mypostlist[position].name
        holder.grpname.text = "" + mypostlist[position].grpname
        holder.date.text = "" + mypostlist[position].date
        holder.chatimg.setOnClickListener(View.OnClickListener {
            val i = Intent(activity, ChatActivity::class.java)
            activity.startActivity(i)
        })

        holder.locbtn.setOnClickListener(View.OnClickListener {

            try {
                val navigationIntentUri = Uri.parse("google.navigation:q=" + 17.4469067f + "," + 78.499896) //creating intent with latlng
                val mapIntent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                activity.startActivity(mapIntent)
            }catch (e: Exception){
                Toast.makeText(activity, "Failed to Open Map", Toast.LENGTH_SHORT).show()
            }

        })


    }

    override fun getItemCount(): Int {
        return mypostlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var name: TextView
        var grpname: TextView
        var date: TextView
        var chatimg: ImageView
        var locbtn: ImageView

        init {
            title = itemView.findViewById(R.id.title)
            name = itemView.findViewById(R.id.name)
            grpname = itemView.findViewById(R.id.grpname)
            date = itemView.findViewById(R.id.date)
            chatimg = itemView.findViewById(R.id.chatimg)
            locbtn = itemView.findViewById(R.id.locbtn)
        }
    }
}
