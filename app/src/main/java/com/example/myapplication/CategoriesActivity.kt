package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_products.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class CategoriesActivity : AppCompatActivity() {
    lateinit var presenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        categoriesListView.layoutManager = GridLayoutManager(this, 2)

        val categoriesListUrl =
            "https://gist.githubusercontent.com/KlepachDaria/dd00fbf2882e97307b740a3b7cfed2f5/raw/9b163c1beaaf38acb511919c212f1353d3c0c7aa/categories.json"
        presenter = CategoriesPresenter(categoriesListUrl, view = this, context = this)

    }

    override fun onResume() {
        super.onResume()
        presenter.onAppear()

    }

    fun displayCategories(categories: List<Category>) {
        categoriesListView.adapter = CategoriesAdapter(categories, this)
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
