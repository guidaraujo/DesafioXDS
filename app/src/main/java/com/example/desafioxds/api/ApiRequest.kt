package com.example.desafioxds.api

import android.content.Context
import android.util.Log
import com.example.desafioxds.R
import com.example.desafioxds.model.LoginResponse
import com.example.desafioxds.model.PizzaResponse
import com.google.gson.JsonParseException
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit


class ApiRequest(context: Context) {
    private var retrofit: Retrofit? = null

    private var context: Context? = null

    init {
        this.context = context
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://p3teufi0k9.execute-api.us-east-1.amazonaws.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun parseApiError(response: Response<*>): String {
        var message = "Erro desconhecido codigo " + response.code()

        if (response.code() == 500) message = "Erro interno no servidor code:500"
        if (response.code() == 404) message = "Recurso não encontrado code:404"
        if (response.code() == 203) message = "Não autorizado code:203"
        return message
    }

    private fun treatFailureMessage(t: Throwable): String {
        var message = context!!.getString(R.string.unknown_exception)
        if (t is SocketTimeoutException) {
            message = context!!.getString(R.string.timeout_exception)
        }
        if (t is UnknownHostException) {
            message = context!!.getString(R.string.unknown_host_exception)
        }
        if (t is NullPointerException) {
            message = context!!.getString(R.string.nullpointer_exception)
        }
        if (t is JsonParseException) {
            message = context!!.getString(R.string.jsonparse_exception)
        }
        return message
    }


    fun login(user: String, pass: String, listener: ResponseListener) {
        val service:ApiService = retrofit!!.create(ApiService::class.java)
        val json = JSONObject()

        var body = ""
        try {
             body = json.put("email", user)
                .put("password", pass)
                .toString()
        }catch (e: Exception){
            listener.onFailure(context!!.getString(R.string.json_exception))
        }
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)

        service.login(requestBody).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.isSuccessful&&response.body()!=null) {
                    val responseBody = response.body()!!
                    listener.onSuccess(responseBody)
                } else {
                    listener.onFailure(parseApiError(response))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                listener.onFailure(treatFailureMessage(t))
            }
        })
    }

    fun getPizzaList(listener: ResponseListener){
        val service:ApiService = retrofit!!.create(ApiService::class.java)

        service.getPizzaList().enqueue(object : Callback<List<PizzaResponse>> {
            override fun onResponse(call: Call<List<PizzaResponse>>, response: Response<List<PizzaResponse>>) {

                var responseBody:List<PizzaResponse> = ArrayList()
                if (response.isSuccessful&&response.body()!=null) {
                    responseBody = response.body()!!
                    listener.onSuccess(responseBody)
                } else {
                    listener.onFailure(parseApiError(response))
                }
            }

            override fun onFailure(call: Call<List<PizzaResponse>>, t: Throwable) {
                listener.onFailure(treatFailureMessage(t))
            }
        })
    }

    fun pizzaString(listener: ResponseListener){
        val service:ApiService = retrofit!!.create(ApiService::class.java)

        service.pizzaString().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("teste", "onResponse: "+response.body()?.string())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("teste", "FALIUER")
            }
        })

    }
}