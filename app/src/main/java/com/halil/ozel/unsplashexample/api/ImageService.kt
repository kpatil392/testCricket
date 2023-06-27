package com.halil.ozel.unsplashexample.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {

    @GET("/nzin01312019187360.json")
    suspend fun getMatchDetailBody():Response<ResponseBody>
}