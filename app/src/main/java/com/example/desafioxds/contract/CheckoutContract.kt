package com.example.desafioxds.contract

import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.desafioxds.BasePresenter
import com.example.desafioxds.BaseView
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.presenter.CheckoutPresenter

class CheckoutContract {
    interface View:BaseView<CheckoutPresenter>{
        fun setData(item:PizzaResponse?)

        fun showErrorMessage(message:String, finish:Boolean=false)
    }

    interface Presenter:BasePresenter{
        fun loadData(intent: Intent)

        fun changeButtonColor(view:Button, size:String)

        fun showDialog()
    }
}