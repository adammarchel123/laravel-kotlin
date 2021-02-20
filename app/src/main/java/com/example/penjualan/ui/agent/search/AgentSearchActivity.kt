package com.example.penjualan.ui.agent.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan.R
import com.example.penjualan.data.Constant
import com.example.penjualan.data.model.agent.DataAgent
import com.example.penjualan.data.model.agent.ResponseAgentList
import kotlinx.android.synthetic.main.activity_agent_search.*

class AgentSearchActivity : AppCompatActivity(), AgentSearchContract.View {

    private lateinit var agentSearchPresenter: AgentSearchPresenter
    private lateinit var agentSearchAdapter: AgentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)
        agentSearchPresenter = AgentSearchPresenter(this)
        agentSearchPresenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Pilih  Agen"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        agentSearchAdapter = AgentSearchAdapter(
            this,
            arrayListOf()
        ) { dataAgent: DataAgent, position: Int ->
            Constant.AGENT_ID = dataAgent.kd_agen!!
            Constant.AGENT_NAME = dataAgent.nama_toko!!
            Constant.IS_CHANGED = true
            finish()
        }

        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                agentSearchPresenter.searchAgent(edtSearch.text.toString())
                true
            } else {
                false
            }
        }

        rcvAgent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentSearchAdapter
        }

        swipe.setOnRefreshListener {
            agentSearchPresenter.getAgent()
        }
    }

    override fun onLoadingAgent(loading: Boolean) {
        when (loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgent(responseAgentList: ResponseAgentList) {
        val dataAgent: List<DataAgent> = responseAgentList.dataAgent
        agentSearchAdapter.setData(dataAgent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}