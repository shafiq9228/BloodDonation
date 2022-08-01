package com.pharma.blooddonate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class HomePage : Fragment() {
    var allpostlist: RecyclerView? = null
    lateinit var fab: FloatingActionButton
    lateinit var auth:FirebaseAuth
    lateinit var logout1: ImageView
    var mypostlist: ArrayList<PostListModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.homepage, container, false)

        auth = FirebaseAuth.getInstance()
        fab = view.findViewById(R.id.fab)
        logout1 = view.findViewById(R.id.logout1)

        fab.setOnClickListener(View.OnClickListener {
            val  i = Intent(activity, UploadActivity::class.java)
            startActivity(i)
        })


        logout1.setOnClickListener(View.OnClickListener {
            val  googleSignInClient = GoogleSignIn.getClient(
                requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN
            )
            googleSignInClient.signOut().addOnCompleteListener(OnCompleteListener { task->
                if (task.isSuccessful){
                    auth.signOut()
                    val i = Intent(activity, MainActivity::class.java)
                    startActivity(i)
                    requireActivity().finishAffinity()
                }else{
                    Toast.makeText(activity, "Failed ", Toast.LENGTH_SHORT).show()
                }
            })
        })

//        allpostlist = view.findViewById(R.id.allpostlist)
//        allpostlist?.setLayoutManager(
//            LinearLayoutManager(
//                context,
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//        )
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
       // allpostlist?.setAdapter(PostListAdapter(mypostlist!!))
        return view
    }
}
