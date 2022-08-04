package com.pharma.blooddonate

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class MapAct : AppCompatActivity(), OnMapReadyCallback {


    private var mMap: GoogleMap? = null

    var maploc: String? = null
    var savebtn: Button? = null

    var pd: ProgressDialog? = null
    var maplayout: LinearLayout? = null

    var myred: ImageView? = null
    var formlayout: LinearLayout? = null

    var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    var marker: Marker? = null
    var locationRequest: LocationRequest? = null


    var flatnum: EditText? =
        null
    var landmark:EditText? = null
    var address1:EditText? = null
    var address2:EditText? = null
    var city:EditText? = null
    var pincode:EditText? = null
    var mymaploc: String? = null
    var mapbutton: Button? = null
    var statespin: Spinner? = null
    var arrayAdapter: ArrayAdapter<String>? = null
    var mystate: String? = null


    var states = arrayOf(
        "Andhra Pradesh",
        "Arunachal Pradesh",
        "Assam",
        "Bihar",
        "Chhattisgarh",
        "Goa",
        "Gujarat",
        "Haryana",
        "Himachal Pradesh",
        "Jammu and Kashmir",
        "Jharkhand",
        "Karnataka",
        "Kerala",
        "Madhya Pradesh",
        "Maharashtra",
        "Manipur",
        "Meghalaya",
        "Mizoram",
        "Nagaland",
        "Odisha",
        "Punjab",
        "Rajasthan",
        "Sikkim",
        "Tamil Nadu",
        "Telangana",
        "Tripura",
        "Uttar Pradesh",
        "Uttarakhand",
        "West Bengal"
    )


    var T1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)



        pd = ProgressDialog(this)
        pd!!.setTitle("Please Wait")
        pd!!.setCancelable(false)
        locationRequest = LocationRequest.create()




        T1 = findViewById(R.id.t1)
        savebtn = findViewById(R.id.savebtn)
        myred = findViewById(R.id.myred)
        formlayout = findViewById(R.id.formlayout)
        maplayout = findViewById(R.id.maplayout)

        mymaploc = intent.getStringExtra("maploc")
        mystate = states[0]
        flatnum = findViewById(R.id.flatnum)
        landmark = findViewById(R.id.landmark)
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)
        city = findViewById(R.id.city)
        pincode = findViewById(R.id.pincode)
        statespin = findViewById(R.id.statespin)
        arrayAdapter =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, states)
        statespin?.setAdapter(arrayAdapter)
        statespin?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                mystate = states[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


        mapbutton = findViewById(R.id.mapsubmit)
        mapbutton?.setOnClickListener(View.OnClickListener {
            if (flatnum!!.getText().toString().isEmpty() || address1?.getText().toString()
                    .isEmpty() || address2?.getText().toString().isEmpty() ||
                landmark?.getText().toString().isEmpty() || pincode?.getText().toString()
                    .isEmpty() || city?.getText().toString().isEmpty()
            ) {
                Toast.makeText(this@MapAct, "Please Fill All Feilds", Toast.LENGTH_SHORT)
                    .show()
            } else {

                finish()
            }
        })
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapAct)
        savebtn!!.setOnClickListener(View.OnClickListener {
            if (T1?.getText().toString().isEmpty() || T1?.getText().toString() == null) {
                Toast.makeText(this@MapAct, "Please Choose Address ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                maplayout?.setEnabled(false)
                mMap!!.uiSettings.setAllGesturesEnabled(false)
                mMap!!.uiSettings.isMyLocationButtonEnabled = false
                formlayout?.setVisibility(View.VISIBLE)
            }
        })
    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        pd!!.show()
        getlocation(googleMap)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MapAct, PERMISSIONS, 1)
            return
        }
        mMap!!.isMyLocationEnabled = true
    }

    fun getlocation(mymap: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                this@MapAct,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MapAct, PERMISSIONS, 1)
            Toast.makeText(this, "Please Add Permission", Toast.LENGTH_SHORT).show()
            return
        }
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    super.onLocationResult(locationResult)
                    LocationServices.getFusedLocationProviderClient(this@MapAct)
                        .removeLocationUpdates(this)
                    if (locationResult != null && locationResult.getLocations().size > 0) {
                        val index: Int = locationResult.getLocations().size - 1
                        val latitude: Double =
                            locationResult.getLocations().get(index).getLatitude()
                        val longitute: Double =
                            locationResult.getLocations().get(index).getLongitude()
                        val mylocation = LatLng(latitude, longitute)
                        marker = mymap.addMarker(
                            MarkerOptions().position(mylocation).title("my mark")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                        )
                        marker!!.isVisible = false
                        Toast.makeText(this@MapAct, "" + mylocation, Toast.LENGTH_SHORT)
                            .show()
                        mymap.moveCamera(CameraUpdateFactory.newLatLng(mylocation))
                        mymap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20f))
                    }
                    mymap.setOnCameraIdleListener {
                        val lng = mMap!!.cameraPosition.target
                        marker!!.isVisible = true
                        myred!!.visibility = View.GONE
                        marker!!.position = lng

                        val geocoder = Geocoder(applicationContext, Locale.getDefault())
                        pd!!.dismiss()
                        try {
                            val addresses = geocoder.getFromLocation(lng.latitude, lng.longitude, 1)
                            marker!!.title = addresses[0].getAddressLine(0)
                            marker!!.showInfoWindow()
                            maploc = addresses[0].getAddressLine(0)
                            T1!!.text = "" + addresses[0].getAddressLine(0)
                        } catch (e: IndexOutOfBoundsException) {
                            Toast.makeText(
                                this@MapAct,
                                "Failed " + e.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            e.printStackTrace()
                        } catch (me: IOException) {
                            Toast.makeText(
                                this@MapAct,
                                "Failed " + me.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            me.printStackTrace()
                        }
                    }
                    mymap.setOnCameraMoveListener {
                        marker!!.isVisible = false
                        myred!!.visibility = View.VISIBLE
                    }
                }
            }, Looper.getMainLooper())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults != null){
            if (grantResults[0] == RESULT_OK){
                mMap?.let { getlocation(it) }
            }
        }
    }
}


