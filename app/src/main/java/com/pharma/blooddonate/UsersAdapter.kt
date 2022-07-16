package com.pharma.blooddonate

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class UsersAdapter(var arrayList: ArrayList<UserModel>, var activity: Activity) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.useritem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {

            val i =  Intent(activity, ChatActivity::class.java).apply {
                this.putExtra("userid", arrayList.get(position).userid);
                this.putExtra("roomid", arrayList.get(position).roomid);
                this.putExtra("name", arrayList.get(position).name);


            }
            activity.startActivity(i);

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userimg: ImageView
        var usertitle: TextView
        var usermessage: TextView
        var chatdate: TextView

        init {
            userimg = itemView.findViewById(R.id.userimg)
            usertitle = itemView.findViewById(R.id.usertitle)
            usermessage = itemView.findViewById(R.id.usermessage)
            chatdate = itemView.findViewById(R.id.chatdate)
        }
    }
}