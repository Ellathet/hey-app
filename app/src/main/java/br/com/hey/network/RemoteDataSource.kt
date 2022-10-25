package br.com.hey.network

import br.com.hey.model.ErrorResponse
import br.com.hey.model.NotificationsResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: IApiService) {

    fun getNotifications(onResponse: (NotificationsResponse?, Throwable?) -> Unit) {
            apiService.getNotifications().enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {

                    if(response.isSuccessful) {
                        val responseString = response.body()?.string()
                        val response = Gson().fromJson(responseString, NotificationsResponse::class.java)
                        onResponse(response, null)
                    } else {
                        val responseErrorString = response.errorBody()?.string()
                        val response = Gson().fromJson(responseErrorString, ErrorResponse::class.java)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResponse(null, t)
                }

            })
    }
}