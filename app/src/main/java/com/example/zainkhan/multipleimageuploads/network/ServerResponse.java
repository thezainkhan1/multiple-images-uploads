package com.example.zainkhan.multipleimageuploads.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zain.Khan on 11-Apr-18.
 */


    public class ServerResponse {

        // variable name should be same as in the json response from php
        @SerializedName("success")
        boolean success;

        @SerializedName("message")
        String message;

        public String getMessage() {
            return message;
        }

        public boolean getSuccess() {
            return success;
        }

    }

