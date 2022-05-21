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
import com.pharma.blooddonate.Models.PostModel
import com.pharma.blooddonate.R

class PostAdapter (val activity: Activity, val arrayList: ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.postitem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pname.setText(arrayList.get(position).name)
        holder.ptitle.setText(arrayList.get(position).title)
        holder.pdesc.setText(arrayList.get(position).desc)
       if (arrayList.get(position).image.equals("")){
           holder.pimage.visibility = View.GONE
       }
        else{
            Glide.with(activity)
                .load(arrayList.get(position).image)
                .into(holder.pimage)

       }

        holder.commentbtn.setOnClickListener(View.OnClickListener {
            Toast.makeText(activity, "post id"+ arrayList.get(position).postid, Toast.LENGTH_SHORT).show()
            val i = Intent(activity, CommentsActivity::class.java)
            i.putExtra("postid", arrayList.get(position).postid)
            activity.startActivity(i)
        })

    }

    override fun getItemCount(): Int {

      return  arrayList.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        var pname: TextView
        var ptitle: TextView
        var commentbtn: TextView
        var pdesc: TextView
        var pimage: ImageView

        init {
            pname = itemView.findViewById(R.id.pname)
            ptitle = itemView.findViewById(R.id.ptitle)
            pdesc = itemView.findViewById(R.id.pdesc)
            commentbtn = itemView.findViewById(R.id.commentsbtn)
            pimage = itemView.findViewById(R.id.pimage)

        }
    }
}