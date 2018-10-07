package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R


class ForAuthorsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.for_authors))
        val view = inflater.inflate(R.layout.fragment_for_authors, container, false)

        val textView = view.findViewById<TextView>(R.id.more_for_authors)
        textView.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            val fragment = PDFFragment()
            val bundle = Bundle()
            val res = resources
            val conf = res.configuration
            if (conf.locale.language == "en")
                bundle.putString("link", "http://bijournal.styleru.org/en/files/avtoram.pdf")
            else
                bundle.putString("link", "http://bijournal.styleru.org/files/avtoram.pdf")

            fragment.arguments = bundle
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
        return view
    }

}
