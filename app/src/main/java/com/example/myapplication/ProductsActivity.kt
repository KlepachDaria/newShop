package com.example.myapplication


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_products.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class ProductsActivity : AppCompatActivity() {

    lateinit var presenter: ProductsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productsListView.layoutManager = GridLayoutManager(this, 2)

        val productsUrl = intent.getStringExtra("productsUrl")
        presenter = ProductsPresenter(productsUrl, view = this, context = this)

    }

    override fun onResume() {
        super.onResume()
        presenter.onAppear()

    }


    fun displayProducts(products: List<Products>) {
        productsListView.adapter = ProductsAdapter(products, this@ProductsActivity)
        loadingView.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {
        presenter.onReturn()
    }

    fun displayError() {

    }

    fun showExitAlert() {
        alert("Вы действительно хотите выйти?") {
            yesButton { dialog ->
                super.onBackPressed()
            }
            noButton { dialog ->

            }
        }.show()
    }




}




