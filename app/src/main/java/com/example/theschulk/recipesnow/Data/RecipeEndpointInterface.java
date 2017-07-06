package com.example.theschulk.recipesnow.Data;

import com.example.theschulk.recipesnow.Models.RecipeModel;

import retrofit2.Call;
import retrofit2.http.GET;



public interface RecipeEndpointInterface {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<RecipeModel> getRecipes();
}
