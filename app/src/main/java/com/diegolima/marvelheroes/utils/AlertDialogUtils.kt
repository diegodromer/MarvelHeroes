package com.diegolima.marvelheroes.utils

import android.app.Activity
import android.app.AlertDialog
import com.diegolima.marvelheroes.R

fun Activity.showAlertError(function: () -> Unit){
    AlertDialog.Builder(this)
        .setTitle(R.string.netwok_alert_dialog)
        .setMessage(R.string.network_alert_message)
        .setPositiveButton(R.string.network_alert_yes) { dialog, _ ->
            function()
            dialog.dismiss()
        }
        .setNegativeButton(R.string.network_alert_no) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}