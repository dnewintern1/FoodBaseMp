package com.base.foodbasemp.Listeners;

import com.base.foodbasemp.model.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {

    void  didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);

}
