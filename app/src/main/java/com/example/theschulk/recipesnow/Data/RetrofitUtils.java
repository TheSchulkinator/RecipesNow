package com.example.theschulk.recipesnow.Data;



public class RetrofitUtils {

    public static final String baseUrl = "https://d17h27t6h515a5.cloudfront.net/";
    public static RecipeEndpointInterface getRecipeEndpointInterface(){
        return RetrofitClient.getRetrofitClient(baseUrl).create(RecipeEndpointInterface.class);
    }
}
