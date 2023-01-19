package com.example.suitmediaapp.services.retrofit
import com.example.suitmediaapp.model.ListUserModel
import retrofit2.Call
import retrofit2.http.*

interface APIEndpoint {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: String
    ): Call<ListUserModel>
}