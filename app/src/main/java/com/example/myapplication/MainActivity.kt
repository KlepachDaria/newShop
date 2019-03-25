package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val okDeferred = GlobalScope.async {
                delay(3000)
                "OK"
            }
            val okResult = okDeferred.await()
        }

        listButton.onClick {
            longToast(R.string.LoginAlert)
            startActivity<CategoriesActivity>()

        }

    }


}
