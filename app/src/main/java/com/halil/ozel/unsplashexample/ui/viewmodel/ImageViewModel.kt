package com.halil.ozel.unsplashexample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halil.ozel.unsplashexample.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repo: ImageRepository) : ViewModel() {
      private val mCricketRespDataStr = MutableLiveData<ResponseBody>()
    val mCricketDataStr: LiveData<ResponseBody> get() = mCricketRespDataStr


    init {
        getMatchStr()
    }

    fun getMatchStr() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getMatchDetailBody().let { response ->
                if (response.isSuccessful) {
                    mCricketRespDataStr.postValue(response.body())
                } else {
                    println("Error ${response.errorBody()}")
                }
            }

        }

    }
}