package com.example.penjualan.ui.agent

import com.example.penjualan.data.model.agent.ResponseAgentDetail
import com.example.penjualan.data.model.agent.ResponseAgentList
import com.example.penjualan.data.model.agent.ResponseAgentUpdate
import com.example.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentPresenter (var view: AgentContract.View) : AgentContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endPoint.getAgent().enqueue(object : Callback<ResponseAgentList> {


            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoadingAgent(false)
            }

            override fun onResponse(
                call: Call<ResponseAgentList>,
                response: Response<ResponseAgentList>
            ) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) {
                    val responseAgentList: ResponseAgentList? = response.body()
                    view.onResultAgent(responseAgentList!!)
                }
            }
        })
    }

    override fun deleteAgent(kd_agen: Long) {
        view.onLoadingAgent(true)
        ApiService.endPoint.deleteAgent(kd_agen).enqueue(object : Callback<ResponseAgentUpdate> {
            override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                view.onLoadingAgent(false)
            }

            override fun onResponse(
                call: Call<ResponseAgentUpdate>,
                response: Response<ResponseAgentUpdate>
            ) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) {
                    val responseAgentUpdate: ResponseAgentUpdate? = response.body()
                    view.onResultDelete(responseAgentUpdate!!)
                }
            }
        })
    }
}































