package br.com.hey.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {

    @GET
    fun getNotifications() : Call<ResponseBody>

}