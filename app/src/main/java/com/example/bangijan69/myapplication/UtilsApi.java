package com.example.bangijan69.myapplication;

/**
 * Created by bangijan69 on 1/31/2018.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.1.173/retrofit/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
