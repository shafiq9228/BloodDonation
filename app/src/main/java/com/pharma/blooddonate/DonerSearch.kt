package com.pharma.blooddonate

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DonerSearch: Fragment() {

    lateinit var filterbtn: ImageView
    lateinit var donerrv: RecyclerView

    var myfilter: String = ""

    var showarray = arrayOf(
        "Location",
        "Blood Group",
        "Date"
    )



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.donersearch, container, false)

        val builder = AlertDialog.Builder(
            requireContext()
        )
        builder.setTitle("Search")
        builder.setSingleChoiceItems(showarray, 0,
            DialogInterface.OnClickListener { dialog, which ->

                myfilter = showarray[which]

            })
        builder.setPositiveButton(
            "OK"
        ) {
                dialog, which ->

            addList()
        }
        builder.setNegativeButton("Cancel", null)

        filterbtn = view.findViewById(R.id.filterbtn)
        donerrv = view.findViewById(R.id.donerrv)
        donerrv.layoutManager = LinearLayoutManager(activity)
        filterbtn.setOnClickListener(View.OnClickListener {

            builder.create()
            builder.show()
        })

        return view
    }


    fun addList(){

        val array = arrayListOf<PostListModel>(
         PostListModel("Donation", "India Foundation", "A+", "19/02/2022"),
            PostListModel("Donation2", "Mark Hospital", "B+", "20/4/2011"),
        )

        donerrv.adapter = activity?.let { PostListAdapter(array, it) }

    }
}