package com.pharma.blooddonate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomePage : Fragment() {
    var allpostlist: RecyclerView? = null
    var mypostlist: ArrayList<PostListModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.homepage, container, false)
        allpostlist = view.findViewById(R.id.allpostlist)
        allpostlist?.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        mypostlist = ArrayList<PostListModel>()
        val listModel = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel)
        val listModel2 = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel2)
        val listModel3 = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel3)
        val listModel4 = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel4)
        val listModel5 = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel5)
        val listModel6 = PostListModel("Donating Blood", "John doe Fondation", "A+", "25/07/2022")
        mypostlist!!.add(listModel6)
        allpostlist?.setAdapter(PostListAdapter(mypostlist!!))
        return view
    }
}
