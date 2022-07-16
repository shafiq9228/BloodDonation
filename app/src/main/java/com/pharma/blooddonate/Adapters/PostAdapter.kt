package com.pharma.blooddonate.Adapters

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pharma.blooddonate.CommentsActivity
import com.pharma.blooddonate.DetailPage
import com.pharma.blooddonate.Models.PostModel
import com.pharma.blooddonate.R

class PostAdapter (val activity: Activity, val arrayList: ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.mypostitem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(activity, DetailPage::class.java)
             activity.startActivity(i)
        })

    }

    override fun getItemCount(): Int {

      return  arrayList.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    }
}