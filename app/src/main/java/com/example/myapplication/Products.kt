package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
class Products(
    val title: String,
    val imageUrl: String,
    val description: String
)
