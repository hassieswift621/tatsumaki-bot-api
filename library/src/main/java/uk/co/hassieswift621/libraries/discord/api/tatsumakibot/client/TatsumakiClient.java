/*
 * Copyright ©2018 HassieSwift621.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.client;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.asyncthreader.AsyncThreader;
import uk.co.hassieswift621.libraries.asyncthreader.Callback;
import uk.co.hassieswift621.libraries.asyncthreader.Request;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiIOException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiJSONException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.TatsumakiUserImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:50.
 */
class TatsumakiClientImpl implements TatsumakiClient {

    private final String BASE_URL = "https://api.tatsumaki.xyz/";
    private final String apiKey;

    public TatsumakiClientImpl(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void getUser(long userId, IResponse response, IError error) {
        final String ENDPOINT_URL = "users/";
        sendResponse(ENDPOINT_URL + userId, response, error);
    }

    @Override
    public void getUser(String userId, IResponse response, IError error) {
        final String ENDPOINT_URL = "users/";
        sendResponse(ENDPOINT_URL + userId, response, error);
    }

    private void sendResponse(String requestURL, IResponse responseCallback, IError errorCallback) {

        // Create async threader.
        AsyncThreader asyncThreader = new AsyncThreader.Builder()
                .setThreadPoolSize(1)
                .build();

        Request<JSONObject> request = new Request<>(
                () -> {

                    try {

                        // Get JSON.
                        OkHttpClient httpClient = new OkHttpClient();
                        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                                .url(BASE_URL + requestURL)
                                .addHeader("Authorization", apiKey)
                                .get()
                                .build();

                        Response response = httpClient.newCall(httpRequest).execute();

                        // Convert input stream to JSON object.
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];

                        int length = response.body().byteStream().read(buffer);
                        while (length != -1) {
                            outputStream.write(buffer, 0, length);
                            length = response.body().byteStream().read(buffer);
                        }

                        return new JSONObject(outputStream.toString("UTF-8"));

                    } catch (IOException e) {
                        throw new TatsumakiIOException("Tatsumaki Bot API IO Exception - Failed to get response", e);
                    } catch (JSONException e) {
                        throw new TatsumakiJSONException("Tatsumaki Bot API JSON Exception - Failed to parse response", e);
                    }
                },
                new Callback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        // Execute the callback and shutdown the async threader.
                        try {
                            TatsumakiUser tatsumakiUser = new TatsumakiUserImpl(response);
                            responseCallback.onResponse(tatsumakiUser);
                        } catch (TatsumakiJSONException e) {
                            errorCallback.onError(e);
                        }

                        asyncThreader.shutdown();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        // Execute the callback and shutdown the async threader.
                        errorCallback.onError(throwable);
                        asyncThreader.shutdown();
                    }
                }
        );

        // Execute the task.
        asyncThreader.execute(request);

    }

    @Deprecated
    @Override
    public void getUser(long userId, ResponseCallback callback) {
        final String ENDPOINT_URL = "users/";
        sendResponse(ENDPOINT_URL + userId, callback);
    }

    @Deprecated
    @Override
    public void getUser(String userId, ResponseCallback callback) {
        final String ENDPOINT_URL = "users/";
        sendResponse(ENDPOINT_URL + userId, callback);
    }

    @Deprecated
    private void sendResponse(String requestURL, ResponseCallback callback) {

        // Create async threader.
        AsyncThreader asyncThreader = new AsyncThreader.Builder()
                .setThreadPoolSize(1)
                .build();

        Request<JSONObject> request = new Request<>(
                new Callable<JSONObject>() {
                    @Override
                    public JSONObject call() throws Exception {

                        try {

                            // Get JSON.
                            OkHttpClient httpClient = new OkHttpClient();
                            okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                                    .url(BASE_URL + requestURL)
                                    .addHeader("Authorization", apiKey)
                                    .get()
                                    .build();

                            Response response = httpClient.newCall(httpRequest).execute();

                            // Convert input stream to JSON object.
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];

                            int length = response.body().byteStream().read(buffer);
                            while (length != -1) {
                                outputStream.write(buffer, 0, length);
                                length = response.body().byteStream().read(buffer);
                            }

                            return new JSONObject(outputStream.toString("UTF-8"));

                        } catch (IOException e) {
                            throw new TatsumakiIOException("Tatsumaki Bot API IO Exception - Failed to get response", e);
                        } catch (JSONException e) {
                            throw new TatsumakiJSONException("Tatsumaki Bot API JSON Exception - Failed to parse response", e);
                        }
                    }
                },
                new Callback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        // Execute the callback and shutdown the async threader.
                        try {
                            TatsumakiUser tatsumakiUser = new TatsumakiUserImpl(response);
                            callback.onSuccess(tatsumakiUser);
                        } catch (TatsumakiJSONException e) {
                            callback.onFailure(e);
                        }

                        asyncThreader.shutdown();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        // Execute the callback and shutdown the async threader.
                        callback.onFailure(throwable);
                        asyncThreader.shutdown();
                    }
                }
        );

        // Execute the task.
        asyncThreader.execute(request);
    }
}