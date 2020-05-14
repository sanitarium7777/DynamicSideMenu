package com.github.sanitarium7777.data.model

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    val menu: List<Menu>
) {
    data class Menu(
        val function: MenuFunction,
        val name: String,
        val `param`: String
    )

    enum class MenuFunction {
        @SerializedName("image")
        IMAGE,
        @SerializedName("text")
        TEXT,
        @SerializedName("url")
        URL
    }
}