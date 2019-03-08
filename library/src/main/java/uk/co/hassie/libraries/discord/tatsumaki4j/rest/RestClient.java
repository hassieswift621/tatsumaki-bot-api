/*
 * Copyright ©2018-2019 Hassie.
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

package uk.co.hassie.libraries.discord.tatsumaki4j.rest;

import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.error.TatsumakiError;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildRankedUser;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildUserStats;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.TatsumakiUser;
import uk.co.hassie.libraries.discord.tatsumaki4j.parser.Parser;
import uk.co.hassie.libraries.discord.tatsumaki4j.exception.TatsumakiException;

import java.util.Collections;
import java.util.List;

public class RestClient implements AutoCloseable {

    // User Agent consts.
    private static final String UA_NAME = "Tatsumaki4J";
    private static final String UA_URL = "https://github.com/hassieswift621/tatsumaki4j";
    private static final String UA_VERSION = "0.6.0";

    private final OkHttpClient httpClient;
    private final Parser parser;
    private final RateLimiter rateLimiter;

    public RestClient(String token) {
        // Create HTTP client.
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", token)
                            .header("Content-Type", "application/json")
                            .header("User-Agent", UA_NAME + "/" + UA_VERSION + " (Hassie, " + UA_URL + ")")
                            .method(request.method(), request.body())
                            .build()
                    );
                })
                .build();

        // Create parser.
        parser = new Parser();

        // Create rate limiter.
        rateLimiter = new RateLimiter();
    }

    public List<GuildRankedUser> getGuildLeaderboard(long guildId) throws TatsumakiException {
        try {
            // Create request.
            Request request = new Request.Builder()
                    .get()
                    .url(Endpoint.getGuildLeaderboard(guildId))
                    .build();

            // Wait for rate limit clearance.
            rateLimiter.acquire();

            // Execute request.
            okhttp3.Response response = httpClient.newCall(request).execute();

            // Check if response was successful.
            if (response.isSuccessful()) {
                return Collections.unmodifiableList(parser.parse(response.body().string(),
                        new TypeToken<List<GuildRankedUser>>() {
                        }.getType()));
            } else {
                // Else, parse error response.
                TatsumakiError error = parser.parse(response.body().string(), TatsumakiError.class);

                // Throw.
                throw new TatsumakiException("Failed to get guild with ID " + guildId + ": " + error.getMessage());
            }
        } catch (Exception e) {
            // Throw.
            throw new TatsumakiException("Failed to get guild with ID " + guildId, e);
        }
    }

    public GuildUserStats getGuildUserStats(long guildId, long userId) throws TatsumakiException {
        try {
            // Create request.
            Request request = new Request.Builder()
                    .get()
                    .url(Endpoint.getGuildUserStats(guildId, userId))
                    .build();

            // Wait for rate limit clearance.
            rateLimiter.acquire();

            // Execute request.
            okhttp3.Response response = httpClient.newCall(request).execute();

            // Check if response was successful.
            if (response.isSuccessful()) {
                return parser.parse(response.body().string(), GuildUserStats.class);
            } else {
                // Else, parse error response.
                TatsumakiError error = parser.parse(response.body().string(), TatsumakiError.class);

                // Throw.
                throw new TatsumakiException("Failed to get guild user stats with guild ID " + guildId +
                        " and user ID " + userId + ": " + error.getMessage());
            }
        } catch (Exception e) {
            // Throw.
            throw new TatsumakiException("Failed to get guild user stats with guild ID " + guildId +
                    " and user ID " + userId + guildId, e);
        }
    }

    public Ping getPing() throws TatsumakiException {
        try {
            // Create request.
            Request request = new Request.Builder()
                    .get()
                    .url(Endpoint.getPing())
                    .build();

            // Wait for rate limit clearance.
            rateLimiter.acquire();

            // Execute request.
            okhttp3.Response response = httpClient.newCall(request).execute();

            // Check if response was successful.
            if (response.isSuccessful()) {
                return parser.parse(response.body().string(), Ping.class);
            } else {
                // Else, parse error response.
                TatsumakiError error = parser.parse(response.body().string(), TatsumakiError.class);

                // Throw.
                throw new TatsumakiException("Failed to get ping: " + error.getMessage());
            }
        } catch (Exception e) {
            // Throw.
            throw new TatsumakiException("Failed to get ping", e);
        }
    }

    public TatsumakiUser getUser(long userId) throws TatsumakiException {
        try {
            // Create request.
            Request request = new Request.Builder()
                    .get()
                    .url(Endpoint.getUser(userId))
                    .build();

            // Wait for rate limit clearance.
            rateLimiter.acquire();

            // Execute request.
            okhttp3.Response response = httpClient.newCall(request).execute();

            // Check if response was successful.
            if (response.isSuccessful()) {
                return parser.parse(response.body().string(), TatsumakiUser.class);
            } else {
                // Else, parse error response.
                TatsumakiError error = parser.parse(response.body().string(), TatsumakiError.class);

                // Throw.
                throw new TatsumakiException("Failed to get user with ID " + userId + ": " + error.getMessage());
            }
        } catch (Exception e) {
            // Throw.
            throw new TatsumakiException("Failed to get user with ID " + userId, e);
        }
    }

    /**
     * Closes the rest client by shutting down the executor services.
     */
    @Override
    public void close() {
        // Shutdown OkHttp.
        httpClient.dispatcher().executorService().shutdown();
    }

}
