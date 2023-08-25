package com.base.foodbasemp.Listeners;

import com.base.foodbasemp.model.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {

    void didFetch(List<SimilarRecipeResponse> response , String message);

    void didError(String message);
}
