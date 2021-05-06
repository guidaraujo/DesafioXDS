package com.example.desafioxds.contract

import com.example.desafioxds.BasePresenter
import com.example.desafioxds.BaseView
import com.example.desafioxds.adapter.PizzaListAdapter
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.presenter.PizzaListPresenter

class PizzaListContract {
    interface View:BaseView<PizzaListPresenter>{
        fun onFailureRequest(message:String)

        fun onSuccessRequest(list:ArrayList<PizzaResponse>)
    }
    interface Presenter:BasePresenter{
        fun loadData()

        fun toCheckout(itemPizzaResponse: PizzaResponse)

        fun logout()
    }
}