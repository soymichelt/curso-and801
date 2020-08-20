package com.soymichel.recyclerviewexampleapp

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        configurarWebView()

        if (this.intent != null && this.intent.data != null) {
            val uri: Uri? = this.intent.data
            mywebview.loadUrl(uri.toString())
            url_address.setText(uri.toString())
        }

    }

    private fun configurarAlarma() {
        Log.i(MainActivity.MI_TAG, "MainActivity.configurarAlarma")

        val i = Intent(this, MyReceiver::class.java)

        val alarmUp = PendingIntent.getBroadcast(
            this, 0,
            i,
            PendingIntent.FLAG_NO_CREATE
        ) != null

        if (alarmUp) {
            Log.i(MainActivity.MI_TAG, "Alarm is already active")
            return
        }

        val alarmIntent = PendingIntent.getBroadcast(this, 0, i, 0)

        val alarmManager =
            this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        // Hopefully your alarm will have a lower frequency than this!
        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 20 * 1000, // AlarmManager.INTERVAL_HALF_HOUR,
            1 * 60 * 1000, // AlarmManager.INTERVAL_HALF_HOUR,
            alarmIntent
        )
    }


    fun configurarWebView() {
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        mywebview.webChromeClient = WebChromeClient()
        mywebview.webViewClient = WebViewClient()

    }

    fun gotoweb(v: View) {
        val url = url_address.text.toString()
        mywebview.loadUrl(url)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.alarmMenu -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Are you sure want to set this alarm?")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        configurarAlarma()
                        dialog.dismiss()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()

                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }
}