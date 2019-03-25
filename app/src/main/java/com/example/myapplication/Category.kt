package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val productsUrl: String,
    val name: String,
    val imageUrl: String

)
