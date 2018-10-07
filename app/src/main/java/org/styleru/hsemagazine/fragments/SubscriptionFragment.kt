package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R


class SubscriptionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.subscription))
        return inflater.inflate(R.layout.fragment_subscription, container, false)
    }


}
