package com.example.suitmediaapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmediaapp.model.ListUserModel
import com.example.suitmediaapp.model.UserNameModel
import com.example.suitmediaapp.services.retrofit.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PageThree : AppCompatActivity() {
    lateinit var userRV : RecyclerView
    var listUser = ArrayList<UserNameModel>()
    var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_three)

        supportActionBar?.hide()

        userRV = findViewById(R.id.userProfile_RV)
        val pullToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)


        APIService.endpoint.getUsers("1")
            .enqueue(object : Callback<ListUserModel> {
                override fun onResponse(
                    call: Call<ListUserModel>,
                    response: Response<ListUserModel>
                ) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null) {
                            listUser = responseBody.data
                            userRV.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = UserAdapter(listUser)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ListUserModel>, t: Throwable) {
                    Log.e("HTTP ERROR",t.message,t)
                }
            })
        pullToRefresh.setOnRefreshListener {
            refreshData() // your code
            pullToRefresh.isRefreshing = false
        }
        userRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    counter++
                    if(counter <= 2) {
                        APIService.endpoint.getUsers(counter.toString())
                            .enqueue(object : Callback<ListUserModel> {
                                override fun onResponse(
                                    call: Call<ListUserModel>,
                                    response: Response<ListUserModel>
                                ) {
                                    if (response.isSuccessful) {
                                        val responseBody = response.body()
                                        if (responseBody != null) {
                                            responseBody.data.forEach {
                                                listUser.add(it)
                                            }
                                            userRV.apply {
                                                layoutManager = LinearLayoutManager(context)
                                                adapter = UserAdapter(listUser)
                                            }
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<ListUserModel>, t: Throwable) {
                                    Log.e("HTTP ERROR", t.message, t)
                                }
                            })
                    }else{
                        Toast.makeText(userRV.context,"No More User",Toast.LENGTH_SHORT)
                    }
                }
            }
        })
    }

    private fun refreshData(){
        listUser.clear()
        counter = 1
        APIService.endpoint.getUsers("1")
            .enqueue(object : Callback<ListUserModel> {
                override fun onResponse(
                    call: Call<ListUserModel>,
                    response: Response<ListUserModel>
                ) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null) {

                            listUser = responseBody.data
                            userRV.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = UserAdapter(listUser)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ListUserModel>, t: Throwable) {
                    Log.e("HTTP ERROR",t.message,t)
                }
            })
    }
}