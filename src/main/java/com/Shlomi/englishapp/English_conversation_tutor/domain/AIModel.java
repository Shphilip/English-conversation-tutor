package com.Shlomi.englishapp.English_conversation_tutor.domain;

// Spring Framework imports
import org.springframework.beans.factory.annotation.Value;  // For injecting properties from application.properties
import org.springframework.stereotype.Component;           // For Spring to manage this as a bean

// Our application's entity classes
import com.Shlomi.englishapp.English_conversation_tutor.entities.AIMessage;
import com.Shlomi.englishapp.English_conversation_tutor.entities.UserMessage;

// Google's Gson library for JSON processing (converting Java objects to/from JSON)
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

// OkHttp library for making HTTP requests to Gemini API
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * AIModel class - This is where the actual AI intelligence lives!
 * This class communicates with Google's Gemini API to get smart responses
 */
@Component  // Spring will create and manage an instance of this class
public class AIModel implements ChatModelInterface{

    // These values are automatically injected from application.properties file
    @Value("${gemini.api.key}")    // Gets the API key you set in application.properties
    private String apiKey;
    
    @Value("${gemini.api.url}")    // Gets the API URL for Gemini service
    private String apiUrl;

    // HTTP client for making web requests to Gemini servers
    private final OkHttpClient httpClient;
    
    // JSON parser for converting between Java objects and JSON format
    private final Gson gson;

    /**
     * Constructor - runs when Spring creates this object
     * Sets up the tools we need to communicate with Gemini
     */
    public AIModel() {
        this.httpClient = new OkHttpClient();  // Creates HTTP client for web requests
        this.gson = new Gson();                // Creates JSON parser
    }

    /**
     * Main method - This gets called when user sends a message
     * INPUT: UserMessage from the user
     * OUTPUT: AIMessage with Gemini's intelligent response
     */
    @Override
    public AIMessage getResponse(UserMessage userMessage){
        try {
            // Step 1: Extract the actual text from the user's message
            String userText = userMessage.getMassage();
            
            // Step 2: Send this text to Gemini API and get back AI response
            String geminiResponse = callGeminiAPI(userText);
            
            // Step 3: Create our AIMessage object with Gemini's response
            AIMessage aiMessage = new AIMessage();
            aiMessage.setMassage(geminiResponse);  // Put Gemini's response into our message
            return aiMessage;
            
        } catch (Exception e) {
            // If anything goes wrong (network issues, API problems, etc.)
            // We catch the error and return a friendly message to the user
            System.err.println("Error in AIModel: " + e.getMessage());
            AIMessage errorMessage = new AIMessage();
            errorMessage.setMassage("Sorry, I'm having trouble right now. Please try again.");
            return errorMessage;
        }
    }

    /**
     * Main coordinator method for calling Gemini API
     * This method orchestrates the entire process:
     * 1. Build the request in Gemini's expected format
     * 2. Send HTTP request to Google's servers
     * 3. Parse the response to get just the text we want
     */
    private String callGeminiAPI(String userMessage) throws Exception {
        JsonObject requestBody = buildGeminiRequest(userMessage);    // Create JSON request
        String jsonResponse = sendHttpRequest(requestBody);          // Send to Gemini
        return parseGeminiResponse(jsonResponse);                    // Extract the answer
    }

    /**
     * Builds the JSON request that Gemini API expects
     * Gemini has a very specific format it wants:
     * {
     *   "contents": [
     *     {
     *       "parts": [
     *         {"text": "your message here"}
     *       ]
     *     }
     *   ]
     * }
     */
    private JsonObject buildGeminiRequest(String userMessage) {
        // Create the main request object
        JsonObject requestBody = new JsonObject();
        
        // Create the "contents" array (Gemini expects an array, even with one item)
        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();
        
        // Create the "parts" array inside content
        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();
        
        // Create the prompt that tells Gemini how to behave
        // This is where we tell Gemini it's an English tutor
        String prompt = "You are an English conversation tutor. Help the user practice English conversation. " +
                       "Provide friendly, encouraging responses that help improve their English. " +
                       "User message: " + userMessage;
        
        // Build the JSON structure step by step
        part.addProperty("text", prompt);       // Put our prompt in the "text" field
        parts.add(part);                        // Put the part in the parts array
        content.add("parts", parts);            // Put parts array in content object
        contents.add(content);                  // Put content in contents array
        requestBody.add("contents", contents);  // Put contents in main request
        
        return requestBody;  // Return the complete JSON request
    }

    /**
     * Sends the HTTP POST request to Gemini's servers
     * This is where we actually communicate over the internet
     */
    private String sendHttpRequest(JsonObject requestBody) throws Exception {
        // Convert our JSON object to a string (this is what gets sent over internet)
        String jsonString = gson.toJson(requestBody);
        
        // Create the HTTP request body with proper content type
        RequestBody body = RequestBody.create(
            jsonString,                                    // The JSON string to send
            MediaType.get("application/json; charset=utf-8")  // Tell server it's JSON
        );
        
        // Build the complete HTTP request
        Request request = new Request.Builder()
            .url(apiUrl + "?key=" + apiKey)                // Gemini URL + your API key
            .post(body)                                    // This is a POST request
            .addHeader("Content-Type", "application/json") // Headers Google expects
            .build();
        
        // Actually send the request and get response
        try (Response response = httpClient.newCall(request).execute()) {
            // Check if the request was successful (status code 200)
            if (!response.isSuccessful()) {
                throw new Exception("Gemini API call failed: " + response.code());
            }
            // Return the response body as a string (this contains Gemini's JSON response)
            return response.body().string();
        }
    }

    /**
     * Parses Gemini's complex JSON response to extract just the text we want
     * Gemini returns a complex structure like:
     * {
     *   "candidates": [
     *     {
     *       "content": {
     *         "parts": [
     *           {"text": "The actual AI response we want"}
     *         ]
     *       }
     *     }
     *   ]
     * }
     * We need to navigate through this structure to get the actual text
     */
    private String parseGeminiResponse(String jsonResponse) throws Exception {
        // Convert the JSON string back to a Java object we can work with
        JsonObject response = gson.fromJson(jsonResponse, JsonObject.class);
        
        // Navigate through the JSON structure step by step:
        return response
            .getAsJsonArray("candidates")          // Get the "candidates" array
            .get(0).getAsJsonObject()             // Get the first (and usually only) candidate
            .getAsJsonObject("content")           // Get the "content" object inside
            .getAsJsonArray("parts")              // Get the "parts" array inside content
            .get(0).getAsJsonObject()             // Get the first part
            .get("text").getAsString();           // Finally! Get the actual text response
    }
}
