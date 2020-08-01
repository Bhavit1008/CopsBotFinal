package com.example.newchatui.model

import com.google.gson.annotations.SerializedName

data class StatusList (
    @SerializedName("userId") var id:String,
    @SerializedName("status") var status:String,
    @SerializedName("createdAt") var createdAt:String
)