package com.pharma.blooddonate.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pharma.blooddonate.Adapters.PostAdapter
import com.pharma.blooddonate.Models.PostModel
import com.pharma.blooddonate.PostActivity
import com.pharma.blooddonate.R
import com.pharma.blooddonate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    lateinit var mypostlist: RecyclerView

    lateinit var arraylist: ArrayList<PostModel>;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

       mypostlist = view.findViewById(R.id.mypostlist)
       mypostlist.setLayoutManager(LinearLayoutManager(activity))
        arraylist = ArrayList()

        val model = PostModel("", "", "", "", "");
        arraylist.add(model)

        val model2 = PostModel("", "", "", "", "");
        arraylist.add(model2)

        val model3 = PostModel("", "", "", "", "");
        arraylist.add(model3)

        mypostlist.adapter = activity?.let { PostAdapter(it, arraylist) }

        return view;
    }
}