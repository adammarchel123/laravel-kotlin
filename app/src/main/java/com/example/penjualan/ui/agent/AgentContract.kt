package com.example.penjualan.ui.agent

import com.example.penjualan.data.model.agent.DataAgent
import com.example.penjualan.data.model.agent.ResponseAgentList
import com.example.penjualan.data.model.agent.ResponseAgentUpdate

interface AgentContract {

    interface Presenter {
        fun getAgent()
        fun deleteAgent(kd_agen: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseAgentList: ResponseAgentList)
        fun onResultDelete(responseAgentUpdate: ResponseAgentUpdate)
        fun showDialogDelete(dataAgent: DataAgent, position: Int)
        fun showDialogDetail(dataAgent: DataAgent, position: Int)
        fun showMessage(message: String)
    }
}