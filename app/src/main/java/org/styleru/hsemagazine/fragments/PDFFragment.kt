package org.styleru.hsemagazine.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import kotlinx.android.synthetic.main.fragment_pdf.*
import org.styleru.hsemagazine.MainActivity
import org.styleru.hsemagazine.R
import java.lang.Exception


class PDFFragment : Fragment(), DownloadFile.Listener {

    var adapter: PDFPagerAdapter? = null
    override fun onSuccess(url: String?, destinationPath: String?) {
        adapter = PDFPagerAdapter(context, destinationPath)
        if (pdfViewPager != null)
            pdfViewPager.adapter = adapter
        if (pdf_progress != null)
            pdf_progress.visibility = View.GONE

    }

    override fun onFailure(e: Exception?) {
        Log.d("myLogs", "Fail")
    }

    override fun onProgressUpdate(progress: Int, total: Int) {
        Log.d("myLogs", "Success")
    }

    override fun onStop() {
        super.onStop()
        if (adapter != null)
            adapter!!.close()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).setCustomTitle(resources.getString(R.string.release))

        val bundle = this.arguments
        if (bundle != null) {
            val remoteDpf = RemotePDFViewPager(context, bundle.getString("link"), this)
//            val remoteDpf = RemotePDFViewPager(context, "https://bijournal.hse.ru/data/2018/07/10/1151852330/BI%202(44)%20RU.pdf", this)
        } else {
//            val remoteDpf = RemotePDFViewPager(context, "https://bijournal.hse.ru/data/2018/07/10/1151852330/BI%202(44)%20RU.pdf", this)
        }

        return inflater.inflate(R.layout.fragment_pdf, container, false)
    }

}
