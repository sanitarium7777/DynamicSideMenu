package com.github.sanitarium7777.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.sanitarium7777.R
import com.github.sanitarium7777.data.model.MenuResponse
import com.github.sanitarium7777.data.repository.MenuRepository
import com.github.sanitarium7777.utils.Resource
import kotlinx.coroutines.*

class MainViewModel(private val mainRepository: MenuRepository, private val context: Context) : ViewModel() {

    var currentItemPosition: Int = 0

     fun getMenuItemByPosition(position: Int): MenuResponse.Menu? {
        currentItemPosition = position
        return mainRepository.getMenuJsonFromCache()?.menu?.get(position)
    }

    fun getCurrentMenuItem(): MenuResponse.Menu? {
        return mainRepository.getMenuJsonFromCache()?.menu?.get(currentItemPosition)
    }

    fun getMenu() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMenuJson()))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: context.resources.getString(R.string.error_text)
                )
            )
        }
    }
}