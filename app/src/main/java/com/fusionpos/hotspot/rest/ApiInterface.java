package com.fusionpos.hotspot.rest;


import com.fusionpos.hotspot.model.version_details_modal;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("fusionapi/")
    @FormUrlEncoded
    Call<version_details_modal> getversion(@FieldMap Map<String, String> params);


}
