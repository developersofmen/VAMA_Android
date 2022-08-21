package com.vama.utils

import android.content.Context
import android.content.SharedPreferences
import com.vama.app.AppController

object SharedPreferenceHelper {
    private const val prefName = "vama_pref.xml"
    fun updatePreferences(key: String?, value: String?) {
        val settings: SharedPreferences =
            AppController.applicationContext().getSharedPreferences(
                prefName,
                Context.MODE_PRIVATE
            )
        val editor = settings.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getStringPreferences(key: String): String? {
        val settings: SharedPreferences = AppController.applicationContext().getSharedPreferences(
            prefName,
            Context.MODE_PRIVATE
        )
        return settings.getString(key, "")
    }

}