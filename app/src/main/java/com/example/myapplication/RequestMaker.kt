package com.example.myapplication

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.connectivityManager
import java.io.File

interface RequestMaker {
    fun make(
        url: String,
        onResult: (result: String) -> Unit,
        onError: () -> Unit
    )

}

class OkHttpRequestMaker(
    val context: Context
) : RequestMaker {

    override fun make(
        url: String,
        onResult: (result: String) -> Unit,
        onError: () -> Unit
    ): Unit = run {
        GlobalScope.launch(Dispatchers.Main) {

            val file = File(context.cacheDir, urlToValidFileName(url))

            if (isOnline()) {
                val resultDeferred = GlobalScope.async(Dispatchers.IO) {
                    makeRequest(url)
                }

                try {
                    val result = resultDeferred.await()

                    cache(file, result)

                    onResult(result)
                } catch (e: Exception) {
                    showPreviousResult(file, onResult, onError)
                }
            } else {
                showPreviousResult(file, onResult,onError)
            }

        }
    }

    fun showPreviousResult(
        file: File,
        onResult: (result: String) -> Unit,
        onError: () -> Unit) = run {
        try {
            val previuseResult = file.readText()
            onResult(previuseResult)
        } catch (e: Exception) {
            onError()
        }
    }

    fun cache(file: File, result: String) = run {
        file.parentFile.mkdirs()
        if (file.exists()) {
            file.delete()
        }

        //  https://gist.githubusercontent.com/KlepachDaria/dd00fbf2882e97307b740a3b7cfed2f5/raw/8f069bfe59ed8f590b219a333130c137a6e11278/categories.json

        file.createNewFile()
        file.writeText(result)

        //path/to/file.json
    }


    fun isOnline() = run {
        val netInfo = context.connectivityManager.activeNetworkInfo
        netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun urlToValidFileName(url: String) = url
        .replace("//", "a")
        .replace(":", "b")

    private fun makeRequest(url: String) = run {
        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()


        val response = client.newCall(request).execute()

        response.body()!!.string()
    }
}