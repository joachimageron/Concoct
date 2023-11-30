/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.concocte.data;

import java.io.FileWriter;
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

import com.example.concocte.R;

import org.json.JSONException;
import org.json.JSONObject;

public final class APIRequest {
    private final OkHttpClient client = new OkHttpClient();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Context context;

    public void run() {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/1")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("testerror");
                e.printStackTrace();
                // Gérer l'échec, par exemple, afficher un message d'erreur à l'utilisateur
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    System.out.println("test2");
                    if (!response.isSuccessful()) {
                        throw new IOException("Code inattendu " + response);
                    }

                    // Traiter la réponse de quelque manière que ce soit, par exemple, mettre à jour l'UI
                    final String responseData = responseBody.string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // Mettre à jour l'UI avec responseData
                            System.out.println("reponse API json " + responseData);
                            try {
                                JSONObject jsonObjectPokeAPI = new JSONObject(responseData);
                                System.out.println(jsonObjectPokeAPI);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        });
    }
}
