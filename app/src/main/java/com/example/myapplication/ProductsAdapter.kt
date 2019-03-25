package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*
import org.jetbrains.anko.layoutInflater

class ProductsAdapter(
    val products: List<Products>,
    val context: Context
)
    : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(recyclerView: ViewGroup, viewTipe: Int): ProductViewHolder = run {

        val view = context.layoutInflater.inflate(
            R.layout.product_item, recyclerView, false
        )


        ProductViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.get(position)
        holder.itemView.titleView.text = product.title

        Picasso.get().load(product.imageUrl).resize(50, 50).centerCrop().into(holder.itemView.pictureView)

    }

    class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item)
}