package com.example.desafioxds.contract

import com.example.desafioxds.BasePresenter
import com.example.desafioxds.BaseView
import com.example.desafioxds.adapter.PizzaListAdapter
import com.example.desafioxds.presenter.LoginPresenter

class LoginContract {
    interface View:BaseView<LoginPresenter>{

        fun onFailureLogin(message:String)

        fun onSuccessLogin()
    }
    interface Presenter:BasePresenter{
        fun login(user:String, pass:String)
    }
}