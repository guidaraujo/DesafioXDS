package com.example.desafioxds.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.desafioxds.R
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*

class Utils {

    companion object {
        private var loadingDialog: LoadingDialog? = null

        fun hideLoadingDialog() {
            try {
                if (loadingDialog != null) {
                    if (loadingDialog!!.isAdded) {
                        loadingDialog!!.dismiss()
                    }
                }
            } catch (e: Exception) {
            }
        }

        fun showAlert(context: Context?, title: String?, message: String?) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", null)
            builder.create().show()
        }

        fun showAlert(context: Context?, message: String?, cancelable:Boolean=true) {
            val title = "Atenção"
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setCancelable(cancelable)
                .setMessage(message)
                .setPositiveButton("Ok", null)
            builder.create().show()
        }

        fun showAlert(context: Context?, message: String?, cancelable:Boolean=true, listener:DialogInterface.OnClickListener?=null) {
            val title = "Atenção"
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setCancelable(cancelable)
                .setMessage(message)
                .setPositiveButton("Ok",listener)
                .setNegativeButton("Cancelar", null)
            builder.create().show()
        }

        fun showLoadingDialog(context: FragmentActivity) {
            context.runOnUiThread {
                try {
                    if (loadingDialog != null && loadingDialog!!.isAdded) {
                        loadingDialog!!.dismiss()
                    }
                    val manager = context.supportFragmentManager
                    // close existing dialog fragments
                    val fragment = manager.findFragmentByTag("loading_dialog")
                    if (fragment != null) {
                        manager.beginTransaction().remove(fragment).commit()
                    }
                    loadingDialog = LoadingDialog()
                    loadingDialog!!.isCancelable =
                        false
                    loadingDialog!!.show(manager, "loading_dialog")
                } catch (e: Exception) { }
            }
        }

        fun toast(activity: Activity, message:String){
            activity.runOnUiThread {
                Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
            }
        }
    }
}