package com.example.desafioxds.utils

import android.content.Context

class SharedPrefUtil {
    companion object {
        var KEY_TOKEN = "keytoken"
        var NAME_PREFERENCES = "DESAFIO_XDS"


        fun getString(key: String?, context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(NAME_PREFERENCES, 0)
            return sharedPreferences.getString(key, null)
        }

        fun saveString(key: String?, value: String?, context: Context) {
            val sharedPreferences = context.getSharedPreferences(NAME_PREFERENCES, 0)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getUser(context: Context): String? {
            return getString(KEY_TOKEN, context)
        }
    }
}