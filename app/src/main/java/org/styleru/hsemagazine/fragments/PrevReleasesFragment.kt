package org.styleru.hsemagazine.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_prev_releases.*
import kotlinx.android.synthetic.main.fragment_prev_releases.view.*
import org.styleru.hsemagazine.MagazineApi
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.adapters.PrevReleasesRVAdapter
import org.styleru.hsemagazine.R
import org.styleru.hsemagazine.dataClasses.Edition
import java.util.*


class PrevReleasesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PrevReleasesRVAdapter
    private var dataForRV = ArrayList<Any>()
    private var dataEdition = ArrayList<Edition>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.prev_releases))
        val view = inflater.inflate(R.layout.fragment_prev_releases, container, false)

        getReleases()

        recyclerView = view.prev_releases_rv
        recyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }

    private fun getReleases() {
        val res = resources
        val conf = res.configuration
        if (conf.locale.language == "en")
            MagazineApi.create().getAllReleasesEn()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map {
                        return@map it.body()
                    }
                    .subscribe({ list ->
                        prev_releases_progress.visibility = View.GONE
                        dataEdition = ArrayList(list!!.releases)
                        dataEdition.forEach {
                            if (!dataForRV.contains(it.year))
                                dataForRV.add(it.year)
                            if (!dataForRV.contains(it))
                                dataForRV.add(it)
                            adapter = PrevReleasesRVAdapter(dataForRV, context!!)
                            recyclerView.adapter = adapter
                        }
                    }, { exception ->
                        Log.d("myLogs", "Error " + exception.message)
                    })
        else
            MagazineApi.create().getAllReleases()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map {
                        return@map it.body()
                    }
                    .subscribe({ list ->
                        prev_releases_progress.visibility = View.GONE
                        dataEdition = ArrayList(list!!.releases)
                        dataEdition.forEach {
                            if (!dataForRV.contains(it.year))
                                dataForRV.add(it.year)
                            if (!dataForRV.contains(it))
                                dataForRV.add(it)
                            adapter = PrevReleasesRVAdapter(dataForRV, context!!)
                            recyclerView.adapter = adapter
                        }
                    }, { exception ->
                        Log.d("myLogs", "Error " + exception.message)
                    })
    }

}
