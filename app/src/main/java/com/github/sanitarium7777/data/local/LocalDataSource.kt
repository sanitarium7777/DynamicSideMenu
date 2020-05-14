package com.github.sanitarium7777.data.local

import com.github.sanitarium7777.data.model.MenuResponse

class LocalDataSource() {
    var menuResponse: MenuResponse? = null
    fun getMenuJson() = menuResponse
    fun setMenuJson(json: MenuResponse) {
        menuResponse = json
    }
}