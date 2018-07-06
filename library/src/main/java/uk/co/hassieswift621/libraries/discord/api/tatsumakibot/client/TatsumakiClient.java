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
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.asyncthreader.AsyncThreader;
import uk.co.hassieswift621.libraries.asyncthreader.Request;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiIOException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiJSONException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.TatsumakiUser;
import uk.co.hassieswift621.libraries.jsonio.JsonIO;

import java.io.IOException;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:50.
 */
public class TatsumakiClient {

    private final String BASE_URL = "https://api.tatsumaki.xyz/";
    private final String token;
    private final AsyncThreader asyncThreader;
    private final OkHttpClient httpClient = new OkHttpClient();

    public static class Builder {

        private int threadPoolSize = Runtime.getRuntime().availableProcessors() + 1;
        private String token;

        public Builder setThreadPoolSize(int threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
            return this;
        }

        public Builder setToken(@NotNull String token) {
            this.token = token;
            return this;
        }

        public TatsumakiClient build() {
            return new TatsumakiClient(token, new AsyncThreader.Builder()
                    .setThreadPoolSize(threadPoolSize)
                    .build());
        }
    }

    public TatsumakiClient(@NotNull String token) {
        this.token = token;
        this.asyncThreader = new AsyncThreader();
    }

    private TatsumakiClient(String token, AsyncThreader asyncThreader) {
        this.token = token;
        this.asyncThreader = asyncThreader;
    }

    public void getUser(long userId, IResponse<TatsumakiUser> response, IError error) {
        final String ENDPOINT_URL = "users/";
        getUserResponse(ENDPOINT_URL + userId, response, error);
    }

    public void getUser(String userId, IResponse<TatsumakiUser> response, IError error) {
        final String ENDPOINT_URL = "users/";
        getUserResponse(ENDPOINT_URL + userId, response, error);
    }

    private void getUserResponse(String requestURL, IResponse<TatsumakiUser> responseCallback, IError errorCallback) {

        // Create request.
        Request<TatsumakiUser> request = new Request<>(
                () -> {

                    try {

                        // Get JSON.
                        Response response = httpClient.newCall(
                                new okhttp3.Request.Builder()
                                        .url(BASE_URL + requestURL)
                                        .addHeader("Authorization", token)
                                        .get()
                                        .build())
                                .execute();

                        JSONObject json = JsonIO.toJSON(response.body().byteStream());

                        return new TatsumakiUser(json);

                    } catch (IOException e) {
                        throw new TatsumakiIOException("Tatsumaki Bot API IO Exception - Failed to get response", e);
                    } catch (JSONException e) {
                        throw new TatsumakiJSONException("Tatsumaki Bot API JSON Exception - Failed to parse response", e);
                    }
                },
                responseCallback::onResponse,
                errorCallback::onError
        );

        // Execute request.
        asyncThreader.execute(request);
    }

    /**
     * Shuts down Async Threader and OkHttp to release resources.
     */
    public void shutdown() {
        asyncThreader.shutdown();
        httpClient.dispatcher().executorService().shutdown();
    }

}