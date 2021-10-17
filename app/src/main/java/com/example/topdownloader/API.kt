package com.example.topdownloader

import retrofit2.Call
import retrofit2.http.GET

interface API {
    var link: String

    @get:GET("xml")
    val getfeed: Call<Feed?>?


}