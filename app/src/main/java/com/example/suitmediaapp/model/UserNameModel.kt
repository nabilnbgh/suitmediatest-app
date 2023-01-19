package com.example.suitmediaapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ListUserModel(
    val page        : Int,
    val per_page    : Int,
    val total       : Int,
    val total_pages : Int,
    val data        : ArrayList<UserNameModel>
)


@Parcelize
data class UserNameModel(
    val id          : Int,
    val email       : String,
    val first_name  : String,
    val last_name   : String,
    val avatar      : String
) : Parcelable
