package com.example.zainkhan.multipleimageuploads;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.zainkhan.multipleimageuploads.network.Constants;
import com.example.zainkhan.multipleimageuploads.network.ServerResponse;
import com.example.zainkhan.multipleimageuploads.network.iAppServices;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> filePaths;
    private iAppServices services;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void uploadImage(View view) {
        filePaths = new ArrayList<>();
        FilePickerBuilder.getInstance().setMaxCount(5)
                .setSelectedFiles(filePaths)
                .setActivityTheme(R.style.AppTheme)
                .pickPhoto(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE:
                if(resultCode==RESULT_OK && data!=null)
                {
                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    //use them anywhere

                    try {
                        convertToBase64(filePaths);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Log.d("listSize",""+filePaths.size());
                }
        }
    }
    public void convertToBase64(ArrayList<String> selectedFiles) throws FileNotFoundException {

        List<String> base64strings = new ArrayList<>();
        for (String filepath:selectedFiles
             ) {
            InputStream inputStream = new FileInputStream(filepath);//You can get an inputStream using any IO API
            byte[] bytes;
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bytes = output.toByteArray();
            String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
//            try {
//                encodedString = URLEncoder.encode(encodedString,"UTF-8");
//                Log.d("TEST",encodedString);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            base64strings.add(encodedString);
        }

        Gson gson = new Gson();
        String temp = gson.toJson(base64strings);


        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        services = retrofit.create(iAppServices.class);
        Log.d("listSize",""+base64strings.size());
        final Call<ServerResponse> photoStrings = services.uploadBase64Strings(base64strings);
        photoStrings.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                String message = String.valueOf(response.code());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
}
