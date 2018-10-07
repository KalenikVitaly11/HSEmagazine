package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R


class EthicsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.ethics))
        return inflater.inflate(R.layout.fragment_ethics, container, false)
    }

}
