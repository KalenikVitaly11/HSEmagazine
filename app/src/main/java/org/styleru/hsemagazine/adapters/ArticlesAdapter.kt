package org.styleru.hsemagazine.adapters

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.article_rv_item.view.*
import org.styleru.hsemagazine.R
import org.styleru.hsemagazine.dataClasses.Article
import java.lang.IndexOutOfBoundsException
import android.text.style.UnderlineSpan
import android.text.SpannableString
import android.graphics.Paint.UNDERLINE_TEXT_FLAG
import android.os.Bundle
import android.util.Log
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.fragments.PDFFragment


class ArticlesAdapter(val data: ArrayList<Any>, val context: Context) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.article_rv_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ArticlesAdapter.ArticleViewHolder, position: Int) {
        if (data[position] is Article) {
            holder.closeTopic()
            val article = data[position] as Article
            holder.title.text = article.name
            val content = SpannableString(article.name)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)

            holder.title.text = content
            holder.authors.text = article.author

            holder.layout.setOnClickListener {
                val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

                val fragment = PDFFragment()
                val bundle = Bundle()
                bundle.putString("link", article.link)
                fragment.arguments = bundle

                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack("")
                transaction.commit()
            }

            try {
                if (data[position + 1] is String || position + 1 == data.size)
                    holder.showLine()
            } catch (e: IndexOutOfBoundsException) {
                Log.d("myLogs", "Size = $position")
            }
        } else {
            holder.topic.text = data[position] as String
            holder.showTopic()
        }
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var topic = itemView.article_topic
        var title = itemView.article_title
        var authors = itemView.article_authors
        val line = itemView.article_line
        val layout= itemView.article_layout

        fun showTopic() {
            topic.visibility = View.VISIBLE
            title.visibility = View.GONE
            authors.visibility = View.GONE
        }

        fun closeTopic() {
            topic.visibility = View.GONE
            title.visibility = View.VISIBLE
            authors.visibility = View.VISIBLE
        }

        fun showLine() {
            line.visibility = View.VISIBLE
        }

    }
}