package com.Shlomi.englishapp.English_conversation_tutor.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;


@Component
public class AiModel implements AiModelInterface{


    @Value("${gemini.api.key}") //Get the api key from the application.properties file
    private String apiKey; 

    @Value("${gemini.api.url}") //Get the api url from the application.properties file
    private String apiUrl;

    private final OkHttpClient httpClient = new OkHttpClient(); //Create OkHttpClient instance for making API requests by HTTP POST requests
    private final Gson gson = new Gson(); // Will hold hold the AI model response


    @Override
    public JsonObject sendToModel(JsonObject message) {
        String jsonRequest = gson.toJson(message); //Convert the message to String to have the correct format

        RequestBody body = RequestBody.create( // Create the Request body text 
                jsonRequest,
                MediaType.get("application/json; charset=utf-8")
            );

        Request request = new Request.Builder() // Create the POST request with the api key, url and the request body text
            .url(apiUrl + "?key=" + apiKey)
            .post(body)
            .addHeader("Content-Type", "application/json") 
            .build();
        
        try {
            Response response = httpClient.newCall(request).execute();
            String responseBody = response.body().string();
            return gson.fromJson(responseBody, JsonObject.class);

        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

} 