package com.pharma.blooddonate

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class DonerHome : Fragment() {
    lateinit var logout: ImageView
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      var view = inflater.inflate(R.layout.donerhome, container, false)

        auth = FirebaseAuth.getInstance();

        logout = view.findViewById(R.id.logout)
        logout.setOnClickListener(View.OnClickListener {
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
                    Toast.makeText(activity, "Failed ",Toast.LENGTH_SHORT).show()
                }
            })
        })
        return view
    }
}