package com.base.foodbasemp.Listeners;

import com.base.foodbasemp.model.RecipeDetailsResponse;

public interface RecipeDetailListener {


        void didFetch(RecipeDetailsResponse response, String message);
        void didError(String message);


}
