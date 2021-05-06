package com.example.desafioxds.api

interface ResponseListener {
    fun onSuccess(o: Any)
    fun onFailure(errorMessage: String)
}
