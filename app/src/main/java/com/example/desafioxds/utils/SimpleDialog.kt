package com.example.desafioxds.utils

import com.example.desafioxds.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.desafioxds.databinding.AlertSimpleBinding

class SimpleDialog : DialogFragment() {
    private lateinit var bind:AlertSimpleBinding
    private lateinit var listener: BackButtonListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = AlertSimpleBinding.inflate(layoutInflater)
        val view: View = inflater.inflate(R.layout.alert_simple , container, false)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        setupWidgets()
        return bind.root
    }

    private fun setupWidgets() {
        bind.btnBack.setOnClickListener {
            listener.onBackPressed()
            dismiss()
        }
    }

    private fun setListener(listener: BackButtonListener){
        this.listener = listener
    }

    interface BackButtonListener{
        fun onBackPressed()
    }

    companion object {
        var TAG = "SimpleDialog"
        fun show(activity: FragmentActivity, listener: BackButtonListener) {
            val manager = activity.supportFragmentManager
            val fragment = manager.findFragmentByTag(TAG)
            if (fragment != null) {
                manager.beginTransaction().remove(fragment).commit()
            }
            val simpleDialog = SimpleDialog()
            simpleDialog.setListener(listener)
            simpleDialog.show(manager, TAG)
        }
    }
}