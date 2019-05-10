package com.sa.kotlin_cleanarch.sample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

class Connectivity {
    companion object {

        /**
         * Get the network info
         *
         * @param context
         * @return
         */
        fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        /**
         * Check if there is any connectivity
         *
         * @param context
         * @return
         */
        fun isConnected(context: Context?): Boolean {
            if (context == null)
                return false

            val info = getNetworkInfo(context)
            return info != null && info.isConnected
        }

        /**
         * Check if there is any connectivity to a Wifi network
         *
         * @param context
         * @return
         */
        fun isConnectedWifi(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * Check if there is any connectivity to a mobile network
         *
         * @param context
         * @return
         */
        fun isConnectedMobile(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
        }

        /**
         * Check if there is fast connectivity
         *
         * @param context
         * @return
         */
        fun isConnectedFast(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected && isConnectionFast(
                context, info.type,
                info.subtype
            )
        }

        /**
         * Check if the connection is fast
         *
         * @param type
         * @param subType
         * @return
         */
        private fun isConnectionFast(context: Context, type: Int, subType: Int): Boolean {
            if (type == ConnectivityManager.TYPE_WIFI) {
                val wifiManager = context.getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                if (wifiInfo != null) {
                    val linkSpeed = wifiInfo.linkSpeed //measured using WifiInfo.LINK_SPEED_UNITS
                    AppLogs.e("Connection Speed", "Wifi -> link speed = $linkSpeed")
                    return linkSpeed >= 1
                }
                return true
            } else return if (type == ConnectivityManager.TYPE_MOBILE) {
                when (subType) {
                    TelephonyManager.NETWORK_TYPE_1xRTT -> {

                        AppLogs.e("Connection Speed", "1xRTT -> 50 - 100 kbps")
                        false // ~ 50-100 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_CDMA -> {

                        AppLogs.e("Connection Speed", "CDMA -> 14 - 64 kbps")
                        false // ~ 14-64 kbps
                    }
                    TelephonyManager.NETWORK_TYPE_EDGE -> {

                        AppLogs.e("Connection Speed", "EDGE -> 50 - 100 kbps")
                        false // ~ 50-100 kbps
                    }
                    TelephonyManager.NETWORK_TYPE_EVDO_0 -> {

                        AppLogs.e("Connection Speed", "EVDO 0 -> 400 - 1000 kbps")
                        true // ~ 400-1000 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_EVDO_A -> {

                        AppLogs.e("Connection Speed", "EVDO A -> 600 - 1400 kbps")
                        true // ~ 600-1400 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_GPRS -> {

                        AppLogs.e("Connection Speed", "GPRS -> 100 kbps")
                        false // ~ 100 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_HSDPA -> {

                        AppLogs.e("Connection Speed", "HSDPA -> 2 - 14 mbps")
                        true // ~ 2-14 Mbps
                    }

                    TelephonyManager.NETWORK_TYPE_HSPA -> {

                        AppLogs.e("Connection Speed", "HSPA -> 700 - 1700 kbps")
                        true // ~ 700-1700 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_HSUPA -> {

                        AppLogs.e("Connection Speed", "HSUPA -> 1 - 23 Mbps")
                        true // ~ 1-23 Mbps
                    }

                    TelephonyManager.NETWORK_TYPE_UMTS -> {

                        AppLogs.e("Connection Speed", "UMTS -> 400 - 7000 kbps")
                        true // ~ 400-7000 kbps
                    }
                    /*
                     * Above API level 7, make sure to set android:targetSdkVersion
                     * to appropriate level to use these
                     */

                    TelephonyManager.NETWORK_TYPE_EHRPD // API level 11
                    -> {

                        AppLogs.e("Connection Speed", "EHRPD -> 1-2 mbps")
                        true // ~ 1-2 Mbps
                    }

                    TelephonyManager.NETWORK_TYPE_EVDO_B // API level 9
                    -> {

                        AppLogs.e("Connection Speed", "EVDO B ->  5 mbps")
                        true // ~ 5 Mbps
                    }

                    TelephonyManager.NETWORK_TYPE_HSPAP // API level 13
                    -> {

                        AppLogs.e("Connection Speed", "HSPAP -> 10 - 20 mbps")
                        true // ~ 10-20 Mbps
                    }

                    TelephonyManager.NETWORK_TYPE_IDEN // API level 8
                    -> {

                        AppLogs.e("Connection Speed", "IDEN -> 25 kbps")
                        false // ~25 kbps
                    }

                    TelephonyManager.NETWORK_TYPE_LTE // API level 11
                    -> {

                        AppLogs.e("Connection Speed", "LTE -> 10+ mbps")
                        true // ~ 10+ Mbps
                    }
                    // Unknown

                    TelephonyManager.NETWORK_TYPE_UNKNOWN -> {

                        AppLogs.e("Connection Speed", "Unknown")
                        false
                    }

                    else -> false
                }
            } else {
                false
            }
        }
    }
}