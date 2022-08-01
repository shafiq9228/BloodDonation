package com.pharma.blooddonate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostListAdapter(var mypostlist: ArrayList<PostListModel>) :
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
    }

    override fun getItemCount(): Int {
        return mypostlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var name: TextView
        var grpname: TextView
        var date: TextView

        init {
            title = itemView.findViewById(R.id.title)
            name = itemView.findViewById(R.id.name)
            grpname = itemView.findViewById(R.id.grpname)
            date = itemView.findViewById(R.id.date)
        }
    }
}
