package com.pharma.blooddonate.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.pharma.blooddonate.Models.CommentModel
import com.pharma.blooddonate.R

class CommentAdapter(val activity: Activity, val arrayList: ArrayList<CommentModel>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.commentsitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        holder.cname.setText(arrayList.get(position).name)
        holder.comment.setText(arrayList.get(position).comment)
        holder.cdate.setText(arrayList.get(position).time)
    }

    override fun getItemCount(): Int {

        return arrayList.size

    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

        var cname: TextView
        var comment: TextView
        var cdate: TextView

        init {
            cname = itemView.findViewById(R.id.cname)
            comment = itemView.findViewById(R.id.comment)
            cdate = itemView.findViewById(R.id.cdate)
        }

    }
}