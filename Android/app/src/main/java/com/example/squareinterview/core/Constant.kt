package com.example.squareinterview.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object Constant {

    const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let { connectManager ->
            val capabilities = connectManager.getNetworkCapabilities(connectManager.activeNetwork)
            capabilities?.let { capability ->
                if (capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}