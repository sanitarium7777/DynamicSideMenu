package com.github.sanitarium7777.ui.url

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UrlFragmentViewModel : ViewModel() {
    val url: MutableLiveData<String> = MutableLiveData()
}