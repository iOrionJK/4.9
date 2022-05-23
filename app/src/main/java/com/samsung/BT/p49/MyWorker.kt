package com.samsung.BT.p49

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class TextWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("test_worker", "text_worker_start")
        var s: String = ""
        var c: String = ""
        for (i in 'a'..'z')
            for (j in 'a'..'z')
                for (k in 'a'..'z')
                    for (p in 'a'..'z')
                        for (t in 'a'..'z')
                            for (m in 'a'..'z')
                            {
                                s = i.toString() + j.toString() + k.toString() + p.toString() + t.toString() + m.toString()
                                if (s == "friend")
                                {
                                    c = s
                                    val d = Data.Builder().putString("data_is", c).build()
                                    Log.d("test_worker", "text_worker_stop")
                                    return Result.success(d)
                                }
                            }

        val d = Data.Builder().putString("data_is", c).build()
        Log.d("test_worker", "text_worker_stop")
        return Result.success(d)
    }
}

class LongWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("test_worker", "long_worker_start")
        val data1 = inputData.getString("data_is") ?: "null"
        val data2 = inputData.getInt("data2", 100)
        var p = 0L
        for (i in 1..data1.length*10000)
            for (j in 1..data2)
                p += i%j
        val d = Data.Builder().putLong("result", p).build()
        Log.d("test_worker", "long_worker_stop with rezult $p")
        return Result.success(d)
    }

}