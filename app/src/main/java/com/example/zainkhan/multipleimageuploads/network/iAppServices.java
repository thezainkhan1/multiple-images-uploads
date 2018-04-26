package com.example.zainkhan.multipleimageuploads.network;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface iAppServices {
    
    @POST("api/merchandizingactivity")
    Call<ServerResponse> uploadBase64Strings(@Body List<String> base64String);

}
