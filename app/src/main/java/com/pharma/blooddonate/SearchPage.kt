package com.pharma.blooddonate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class SearchPage : Fragment(), AdapterView.OnItemSelectedListener {
    var allusers: RecyclerView? = null
    var myuserslist: ArrayList<UsersModel>? = null
    var strarr = arrayOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.searchpage, container, false)
        val spinner = view.findViewById<Spinner>(R.id.spinner1)
        val myadapter = ArrayAdapter(
            activity!!,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            strarr
        )
        spinner.adapter = myadapter


//        allusers = view.findViewById(R.id.allusers);
//        allusers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
//        myuserslist = new ArrayList<>();
        return view
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val text = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
