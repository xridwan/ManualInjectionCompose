package com.eve.manualinjection.utils

import android.content.Context
import android.widget.Toast

object Utils {
    const val DB_NAME = "post_db"

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}