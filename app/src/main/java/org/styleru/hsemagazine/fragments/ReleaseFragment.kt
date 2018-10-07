package org.styleru.hsemagazine.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_prev_releases.view.*
import kotlinx.android.synthetic.main.fragment_release.view.*
import org.styleru.hsemagazine.MagazineApi
import org.styleru.hsemagazine.R
import org.styleru.hsemagazine.adapters.ArticlesAdapter
import org.styleru.hsemagazine.dataClasses.Article
import android.view.MotionEvent
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_release.*
import org.styleru.hsemagazine.MainActivity


class ReleaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter
    private lateinit var dataForRV: ArrayList<Any>
    private var link = String()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_release, container, false)
        val button = view.findViewById<Button>(R.id.release_read)
        val readMore = view.findViewById<TextView>(R.id.release_view_prev)
        val bundle = this.arguments

        if (bundle != null)
            getRelease(bundle.getInt("id"))

        button.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction()

            val bundle = Bundle()
            bundle.putString("link", link)
            val fragment = PDFFragment()
            fragment.arguments = bundle

            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("")
            transaction.commit()
        }

        readMore.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, PrevReleasesFragment())
            transaction.addToBackStack("")
            transaction.commit()
        }

        recyclerView = view.release_rv

        return view
    }

    private fun getRelease(id: Int) {
        val res = resources
        val conf = res.configuration
        if (conf.locale.language == "en")
            MagazineApi.create().getReleaseByIdEn(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map { it.body() }
                    .map {
                        (activity as MainActivity).setCustomTitle(it.edition.name)
                        release_title.text = it.edition.name
                        link = it.edition.link
                        it.types
                    }
                    .subscribe({
                        release_progress.visibility = View.GONE
                        dataForRV = ArrayList()
                        it.forEach { element ->
                            if (!dataForRV.contains(element.type.name))
                                dataForRV.add(element.type.name)
                            element.articles.forEach { article ->
                                dataForRV.add(article)
                            }
                        }
                        adapter = ArticlesAdapter(dataForRV, context!!)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = adapter
                    }, {
                        Log.d("myLogs", it.message)
                    })
        else
            MagazineApi.create().getReleaseById(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map { it.body() }
                    .map {
                        (activity as MainActivity).setCustomTitle(it.edition.name)
                        release_title.text = it.edition.name
                        link = it.edition.link
                        it.types
                    }
                    .subscribe({
                        release_progress.visibility = View.GONE
                        dataForRV = ArrayList()
                        it.forEach { element ->
                            if (!dataForRV.contains(element.type.name))
                                dataForRV.add(element.type.name)
                            element.articles.forEach { article ->
                                dataForRV.add(article)
                            }
                        }
                        adapter = ArticlesAdapter(dataForRV, context!!)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = adapter
                    }, {
                        Log.d("myLogs", it.message)
                    })
    }

}
