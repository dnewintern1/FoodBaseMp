package com.base.foodbasemp.Listeners;

import com.base.foodbasemp.model.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {

    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);

}
