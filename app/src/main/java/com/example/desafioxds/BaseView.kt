package com.example.desafioxds

import com.example.desafioxds.adapter.PizzaListAdapter

interface BaseView<T> {
    var presenter:T
    fun setupView()
}