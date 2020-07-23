package com.example.newchatui.api

import com.example.newchatui.model.ComplaintResponse
import com.example.newchatui.model.ImageResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitApi {
    @FormUrlEncoded
    @POST("/api/complaints")
    fun createUser(
        @Field("authorization") authorization:String,
        @Field("victimName") victimName:String,
        @Field("complaint") complaint:String,
        @Field("crimeCategory") crimeCategory:String,
        @Field("age") age:String,
        @Field("gender") gender:String,
        @Field("iLatitude") iLatitude:String,
        @Field("iLongitude") iLongitude:String,
        @Field("landmark") landmark:String,
        @Field("dateOfIncident") dateOfIncident:String,
        @Field("timeOfIncident") timeOfIncident:String
        ): retrofit2.Call<ComplaintResponse>


    @FormUrlEncoded
    @POST("/api/incident")
    fun postImage(
        @Field("authorization") authorization:String,
        @Field("evidence") image:String,
        @Field("incidentDesc") desc:String,
        @Field("iLatitude") latitude:String,
        @Field("iLongitude") longitude:String
    ): retrofit2.Call<ImageResponse>

}
