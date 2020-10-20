package com.ellen.androidrestudy

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv).text = getChannelName(this)
    }


    override fun onResume() {
        super.onResume()
        Toast.makeText(this,getChannelName(this),Toast.LENGTH_LONG).show()
    }

    fun getChannelName(ctx: Activity?): String? {
        if (ctx == null) {
            return null
        }
        var channelName: String? = ""
        try {
            val packageManager = ctx.packageManager
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                val applicationInfo =
                    packageManager.getApplicationInfo(ctx.packageName, PackageManager.GET_META_DATA)
                if (applicationInfo.metaData != null) {
                    channelName = applicationInfo.metaData.getString("APP_CHANNEL")
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return channelName
    }
}