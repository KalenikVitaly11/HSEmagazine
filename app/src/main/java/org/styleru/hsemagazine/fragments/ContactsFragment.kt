package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R

class ContactsFragment : Fragment(), OnMapReadyCallback {

    override fun onMapReady(p0: GoogleMap?) {
        val ll = LatLng(55.72055006900541,37.609244999999994)
        p0?.addMarker(MarkerOptions().position(ll))
        p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 17f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.contacts))
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        return view
    }
}
