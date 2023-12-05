package com.example.concocte.data;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.concocte.MainActivity;
import com.example.concocte.QuizzFragment;
import com.example.concocte.R;

import org.json.JSONException;
import org.json.JSONObject;

// Class to handle API requests
public final class APIRequest {
    // OkHttpClient to send network requests
    private final OkHttpClient client = new OkHttpClient();
    // Handler to post runnable to the main thread
    private final Handler handler = new Handler(Looper.getMainLooper());
    // Context of the application
    private Context context;

    // Method to run the API request
    public void run(QuizzFragment.APIQuizCallback apiQuizCallback, String theme, String count, String difficulty) {
        // Build the request
        Request request = new Request.Builder()
                .url("https://quizzapi.jomoreschi.fr/api/v1/quiz?limit=" + count + "&category=" + theme + "&difficulty=" + difficulty + "\n")
                .build();

        // Enqueue the request
        client.newCall(request).enqueue(new Callback() {
            // Handle failure
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // Handle failure, for example, shows an error message to the user
            }

            // Handle response
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    // Handle the response in some way, for example, update the UI
                    final String responseData = responseBody.string();
                    JSONObject jsonObjectPokeAPI = new JSONObject(responseData);
                    apiQuizCallback.AddListQuestions(jsonObjectPokeAPI);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}