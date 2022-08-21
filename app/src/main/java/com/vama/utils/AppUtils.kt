package com.vama.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.widget.Toast
import com.vama.utils.AppConstant.DATE_FORMAT_1
import com.vama.utils.AppConstant.DATE_FORMAT_2
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    fun isInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }

    fun showToast(mContext: Context?, message: String) {
        if (mContext != null)
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }

    fun parseDate(time: String?): String? {
        val inputPattern = DATE_FORMAT_1
        val outputPattern = DATE_FORMAT_2
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date?
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

    fun openInBrowse(context: Context, url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun getWidth(context: Context): Int {
        var width: Int = pxToDp(context, context.getResources().getDisplayMetrics().widthPixels)
        width -= 40
        return width / 2
    }

    private fun pxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density).toInt()
    }
}