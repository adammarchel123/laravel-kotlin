package com.example.penjualan.ui.transaction.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.penjualan.R
import com.example.penjualan.data.Constant
import com.example.penjualan.data.model.transaction.detail.DataDetail
import com.example.penjualan.data.model.transaction.detail.ResponseTransactionDetail
import kotlinx.android.synthetic.main.fragment_transaction_detail.*

class TransactionDetailFragment : Fragment(), TransactionDetailContract.View {

    lateinit var detailAdapter: TransactionDetailAdapter
    lateinit var presenter: TransactionDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        presenter = TransactionDetailPresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Detail Transaksi"
        presenter.getTransactionByInvoice(Constant.INVOICE)
    }

    override fun initFragment() {
        detailAdapter = TransactionDetailAdapter(requireContext(), arrayListOf())
    }

    override fun initListener(view: View) {
        val txvInvoice = view.findViewById<TextView>(R.id.txvInvoice)
        val rcvDetail = view.findViewById<RecyclerView>(R.id.rcvDetail)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        txvInvoice.text = Constant.INVOICE
        rcvDetail!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionDetail: ResponseTransactionDetail) {
        val dataDetail: List<DataDetail> = responseTransactionDetail.dataDetail
        detailAdapter.setData(dataDetail)
    }
}