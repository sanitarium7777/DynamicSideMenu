package com.github.sanitarium7777.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageFragmentViewModel : ViewModel() {
    val url: MutableLiveData<String> = MutableLiveData()
}