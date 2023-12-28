package com.factorial.common.ext

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.Fragment

class IntentExt {

    companion object {

        fun Context.toInternetSettings() {
            try {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            } catch (e: Exception) {
                Toast.makeText(this, "Go to settings and activate WI-FI", Toast.LENGTH_SHORT).show()
            }
        }

        fun Fragment.toInternetSettings() {
            context?.toInternetSettings()
        }

    }

}