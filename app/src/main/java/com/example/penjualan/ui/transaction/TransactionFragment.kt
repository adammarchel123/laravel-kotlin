package com.example.penjualan.ui.transaction

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.penjualan.R
import com.example.penjualan.data.Constant
import com.example.penjualan.data.database.PrefsManager
import com.example.penjualan.data.model.transaction.DataTransaction
import com.example.penjualan.data.model.transaction.ResponseTransactionList
import com.example.penjualan.ui.cart.CartActivity
import com.example.penjualan.ui.transaction.detail.TransactionDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionFragment : Fragment(), TransactionContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var transactionAdapter: TransactionAdapter
    lateinit var presenter: TransactionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)

        prefsManager = PrefsManager(requireContext())
        presenter = TransactionPresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername(prefsManager.prefsUsername)
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(requireContext(), arrayListOf()) {
            dataTransaction, position ->
            onClickTransaction(dataTransaction.no_faktur!!)
        }
    }

    override fun initListener(view: View) {
        val rcvTransaction = view.findViewById<RecyclerView>(R.id.rcvTransaction)
        val swipe = view.findViewById<(SwipeRefreshLayout)>(R.id.swipe)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        rcvTransaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(prefsManager.prefsUsername)
        }

        fab.setOnClickListener {
            requireContext().startActivity(Intent(context, CartActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
        val dataTransaction: List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {
        Constant.INVOICE = invoice
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, TransactionDetailFragment(), "transDetail")
            .commit()
    }

}

























