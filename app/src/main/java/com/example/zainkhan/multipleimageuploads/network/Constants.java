package com.example.zainkhan.multipleimageuploads.network;


import java.util.Arrays;
import java.util.List;

public class Constants {

    //api url
    //previous url
//    public static final String BASE_URL = "http://yplstapp.youngsfood.com/yplmerchandizingapp/";

    public static final String BASE_URL = "http://localhost/yplrmapp/";


//    public static final String BASE_URL = "http://192.168.1.26/yplrmapp/";
    public static final String API_BASE_URL = BASE_URL+ "api/";

    public static final String CLIENTID     = "2";
    public static final String CLIENTSECRET = "CLrjLjAMSTnXQU32elaaWYUKZfLqXzoSNMs9ZXeW";

    public static final String GRANTTYPE    = "password";
    public static final String CONTENTTYPE  = "";
    public static final String SCOPE        = "*";

    public static final String IMAGE_URL = BASE_URL +"public/45plano876/";

    // Number of columns of Grid View
    public static final int NUM_OF_COLUMNS = 3;


    // Gridview image padding
    public static final int GRID_PADDING = 8; // in dp

    // SD card image directory
    public static final String PHOTO_ALBUM = "PlanogramImages";
//    public static final String PHOTO_ALBUM = Environment.getExternalStorageDirectory() + "/PlanogramImages/";

    // supported file formats
    public static final List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg",
            "png");


    public static final int EXPIRY_PERIOD_IN_DAYS = +90;
}
