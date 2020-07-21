package com.example.newchatui.model

data class ComplaintResponse(
    val error:String,
    val name:String,
    val gender:String,
    val latitude:String,
    val longitude:String,
    val landmark:String,
    val category:String,
    val description:String,
    val token :String,
    val date:String
)