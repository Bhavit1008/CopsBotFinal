package com.example.newchatui.api

import com.example.newchatui.model.ComplaintResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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

}
