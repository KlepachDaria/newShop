package com.example.myapplication

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class CategoriesPresenter(
    val categoriesListUrl: String,
    val view: CategoriesActivity,
    context: Context
) {

    val requestMaker = OkHttpRequestMaker(context)

    fun onAppear() {

        requestMaker.make(
            categoriesListUrl,
            onResult = { categoriesJson ->
                val categories = Json.parse(Category.serializer().list, categoriesJson)
                view.displayCategories(categories)
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

interface CategoriesView {
    fun displayCategories(categories: List<Category>)
    fun showExitAlert()
    fun displayError()
}