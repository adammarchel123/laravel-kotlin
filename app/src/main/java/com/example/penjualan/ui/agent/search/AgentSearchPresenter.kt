package com.example.penjualan.ui.agent.search

import com.example.penjualan.data.model.agent.ResponseAgentList
import com.example.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentSearchPresenter(val view: AgentSearchContract.View) : AgentSearchContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endPoint.getAgent()
            .enqueue(object : Callback<ResponseAgentList> {

                override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

                override fun onResponse(
                    call: Call<ResponseAgentList>,
                    response: Response<ResponseAgentList>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseAgent: ResponseAgentList? = response.body()
                        view.onResultAgent(responseAgent!!)
                    }
                }

            })
    }

    override fun searchAgent(keyword: String) {
        view.onLoadingAgent(true)
        ApiService.endPoint.searchAgent(keyword)
            .enqueue(object : Callback<ResponseAgentList> {

                override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

                override fun onResponse(
                    call: Call<ResponseAgentList>,
                    response: Response<ResponseAgentList>
                ) {
                    view.onLoadingAgent(false)
                    if (response.isSuccessful) {
                        val responseAgent: ResponseAgentList? = response.body()
                        view.onResultAgent(responseAgent!!)
                    }
                }

            })
    }

}