package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.Button
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_journal.*
import org.styleru.hsemagazine.MagazineApi
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R


class JournalFragment : Fragment() {

    var lastReleaseId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.journal))
        val view = inflater.inflate(R.layout.fragment_journal, container, false)
        getLastRelease()
        val button = view.findViewById<Button>(R.id.journal_read)
        button.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction()

            val fragment = ReleaseFragment()
            val bundle = Bundle()
            bundle.putInt("id", lastReleaseId)
            fragment.arguments = bundle

            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
//        adapter.close()
    }

    private fun getLastRelease() {
        val res = resources
        val conf = res.configuration
        if (conf.locale.language == "en")
            MagazineApi.create().getLastReleaseEn()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map { response ->
                        return@map response.body()
                    }
                    .subscribe({ release ->
                        journal_progress.visibility = View.GONE
                        lastReleaseId = release!!.release.id.toInt()
                        journal_last_release_name.text = release.release.name
                    }, { exception ->
                        Log.d("myLogs", exception.message)
                    })
        else
            MagazineApi.create().getLastRelease()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map { response ->
                        return@map response.body()
                    }
                    .subscribe({ release ->
                        journal_progress.visibility = View.GONE
                        lastReleaseId = release!!.release.id.toInt()
                        journal_last_release_name.text = release.release.name
                    }, { exception ->
                        Log.d("myLogs", exception.message)
                    })
    }

}
