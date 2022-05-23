package com.samsung.BT.p49

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    var count: Int = 0
    lateinit var data: Data
    lateinit var process1: OneTimeWorkRequest
    lateinit var process2: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.clicker).setOnClickListener {
            count++
            (it as Button).text = count.toString()
        }

        findViewById<Button>(R.id.startprocess).setOnClickListener {
            process1 = OneTimeWorkRequest.Builder(TextWorker::class.java).addTag("process1").build()
            val d = Data.Builder().putInt("data2", count).build()
            process2 = OneTimeWorkRequest.Builder(LongWorker::class.java).addTag("process2").setInputData(d).build()
            WorkManager.getInstance(this)
                .beginWith(process1)
                .then(process2)
                .enqueue()
        }
    }

    override fun onDestroy() {
        WorkManager.getInstance(this).cancelAllWorkByTag("process1")
        WorkManager.getInstance(this).cancelAllWorkByTag("process2")
        super.onDestroy()
    }
}