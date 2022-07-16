package com.pharma.blooddonate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.pharma.blooddonate.databinding.ActivityDrawer2Binding

class Drawer3Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDrawer2Binding
    lateinit var sh: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawer2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDrawer2.toolbar)

        sh = getSharedPreferences("mypref", MODE_PRIVATE)

        binding.appBarDrawer2.fab.setOnClickListener { view ->
            val i = Intent(this, PostActivity::class.java)
            startActivity(i)
        }

        if (sh.getInt("type", -1) == 0){
            binding.appBarDrawer2.fab.visibility = View.GONE

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_drawer2)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val navigationView1 = findViewById<NavigationView>(R.id.nav_view)


        navigationView1.getMenu().getItem(1)
            .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {

                val i = Intent(this, UserActivity::class.java)
                startActivity(i)
                drawerLayout.closeDrawers()
                false
            })

        navigationView1.getMenu().getItem(2)
            .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {

                val i = Intent(this, SelectionActivity::class.java)
                startActivity(i)
                finishAffinity()
                drawerLayout.closeDrawers()
                false
            })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer3, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}