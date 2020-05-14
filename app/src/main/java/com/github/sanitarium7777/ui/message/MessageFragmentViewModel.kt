package com.github.sanitarium7777.ui.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageFragmentViewModel : ViewModel() {
    val message: MutableLiveData<String> = MutableLiveData()
}