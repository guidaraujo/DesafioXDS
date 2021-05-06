package com.example.desafioxds.contract

import com.example.desafioxds.BasePresenter
import com.example.desafioxds.BaseView
import com.example.desafioxds.presenter.SplashPresenter

class SplashContract {
    interface View:BaseView<SplashPresenter>{
        fun toLogin()

        fun toPizzaList()
    }

    interface Presenter:BasePresenter{
        fun verifyLogin()
    }
}