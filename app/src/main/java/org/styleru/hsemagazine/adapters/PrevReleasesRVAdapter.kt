package org.styleru.hsemagazine.adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pre_releases_item.view.*
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R
import org.styleru.hsemagazine.dataClasses.Edition
import org.styleru.hsemagazine.fragments.ReleaseFragment

class PrevReleasesRVAdapter(val data: ArrayList<Any>, val context: Context) : RecyclerView.Adapter<PrevReleasesRVAdapter.PrevReleasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevReleasesViewHolder {
        return PrevReleasesViewHolder(LayoutInflater.from(context).inflate(R.layout.pre_releases_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PrevReleasesViewHolder, position: Int) {
        if (data[position] is Edition) {
            holder.title.text = (data[position] as Edition).name
            holder.closeYear()
            holder.title.setOnClickListener {
                val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()
                val fragment = ReleaseFragment()

                val bundle = Bundle()
                bundle.putInt("id", (data[position] as Edition).id.toInt())
                fragment.arguments = bundle

                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack("")
                transaction.commit()

            }
        } else {
            holder.year.text = data[position] as String
            holder.showYear()
        }
    }

    class PrevReleasesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var year = itemView.release_item_year
        var title = itemView.release_item_title
        var line = itemView.release_item_line

        fun showYear() {
            year.visibility = View.VISIBLE
            title.visibility = View.GONE
            line.visibility = View.GONE
        }

        fun closeYear() {
            year.visibility = View.GONE
            title.visibility = View.VISIBLE
            line.visibility = View.VISIBLE
        }

    }
}