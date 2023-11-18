package com.fusionpos.hotspot.model;

import com.google.gson.annotations.SerializedName;

public class version_details_modal {



    //AccountResultModel
    @SerializedName("STATUS")
    private String STATUS;
    @SerializedName("RESPONSE_CODE")
    private String RESPONSE_CODE;
    @SerializedName("RESPONSE")
    private String RESPONSE;

    @SerializedName("DATA")
    private String DATA;

    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;




    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }

    public void setRESPONSE_CODE(String RESPONSE_CODE) {
        this.RESPONSE_CODE = RESPONSE_CODE;
    }

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }


    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
