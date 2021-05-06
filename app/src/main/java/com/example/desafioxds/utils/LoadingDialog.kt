package com.example.desafioxds.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.desafioxds.R

class LoadingDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_loading, container, false)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        return view
    }
}