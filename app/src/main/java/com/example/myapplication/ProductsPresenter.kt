package com.example.myapplication

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class ProductsPresenter(
    val productsUrl: String,
    val view: ProductsActivity,
    context: Context
){

    val requestMaker = OkHttpRequestMaker(context)

    fun onAppear(){

        requestMaker.make(
            productsUrl,
            onResult = { productsJson ->
                val products = Json.parse(Products.serializer().list, productsJson)
                view.displayProducts(products)
            },
            onError = {
                view.displayError()
            }
        )

    }
    fun onReturn() {
        view.showExitAlert()
    }
}
interface ProductsView {
    fun displayProducts(products: List<Products>)
    fun showExitAlert()
    fun displayError()
}