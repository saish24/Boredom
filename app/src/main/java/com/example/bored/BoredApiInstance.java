package com.example.bored;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoredApiInstance {
    @GET("activity/")
    Call<BorAct> getmesomething();
}
