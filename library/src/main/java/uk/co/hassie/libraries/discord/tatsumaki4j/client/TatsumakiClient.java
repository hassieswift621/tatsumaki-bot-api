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

package uk.co.hassie.libraries.discord.tatsumaki4j.client;

import uk.co.hassie.libraries.asyncthreader.AsyncThreader;
import uk.co.hassie.libraries.asyncthreader.Request;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildRankedUser;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildUserPoints;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildUserScore;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.guild.GuildUserStats;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.update.UpdateAction;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.TatsumakiUser;
import uk.co.hassie.libraries.discord.tatsumaki4j.rest.RestClient;

import java.util.List;

public class TatsumakiClient implements AutoCloseable {

    private final AsyncThreader asyncThreader;
    private final RestClient restClient;

    /**
     * Tatsumaki client builder.
     */
    public static class Builder {

        private int threadPoolSize;
        private String token;

        /**
         * Sets the thread pool size.
         *
         * @param threadPoolSize The thread pool size.
         * @return The builder instance.
         */
        public Builder setThreadPoolSize(int threadPoolSize) {
            this.threadPoolSize = threadPoolSize;
            return this;
        }

        /**
         * Sets the API token.
         *
         * @param token The API token
         * @return The builder instance.
         */
        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        /**
         * Builds the Tatsumaki client.
         *
         * @return The Tatsumaki client instance.
         */
        public TatsumakiClient build() {
            if (threadPoolSize > 0) {
                return new TatsumakiClient(new AsyncThreader.Builder()
                        .setThreadPoolSize(threadPoolSize)
                        .build(), token);
            } else {
                return new TatsumakiClient(token);
            }
        }

    }

    public TatsumakiClient(String token) {
        asyncThreader = new AsyncThreader();
        restClient = new RestClient(token);
    }

    private TatsumakiClient(AsyncThreader asyncThreader, String token) {
        this.asyncThreader = asyncThreader;
        restClient = new RestClient(token);
    }

    /**
     * Gets the leaderboard for a guild.
     *
     * @param guildId  The guild ID.
     * @param response The response callback.
     * @param error    The error callback.
     */
    public void getGuildLeaderboard(long guildId, Response<List<GuildRankedUser>> response, Error error) {
        Request<List<GuildRankedUser>> request = new Request<>(
                () -> restClient.getGuildLeaderboard(guildId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Gets a user's stats for a guild.
     *
     * @param guildId  The guild ID.
     * @param userId   The user ID.
     * @param response The response callback.
     * @param error    The error callback.
     */
    public void getGuildUserStats(long guildId, long userId, Response<GuildUserStats> response, Error error) {
        Request<GuildUserStats> request = new Request<>(
                () -> restClient.getGuildUserStats(guildId, userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Pings the bot.
     *
     * @param response The response callback.
     * @param error    The error callback.
     */
    public void getPing(Response<Ping> response, Error error) {
        Request<Ping> request = new Request<>(
                restClient::getPing,
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Gets a Tatsumaki user.
     *
     * @param userId   The user's ID.
     * @param response The response callback.
     * @param error    The error callback.
     */
    public void getUser(long userId, Response<TatsumakiUser> response, Error error) {
        Request<TatsumakiUser> request = new Request<>(
                () -> restClient.getUser(userId),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Updates a user's points in a guild.
     *
     * @param guildId      The guild ID.
     * @param userId       The user's ID.
     * @param updateAction The type of update action.
     * @param amount       The amount to update the user's points by.
     * @param response     The response callback.
     * @param error        The error callback.
     */
    public void updateGuildUserPoints(long guildId, long userId, UpdateAction updateAction, int amount,
                                      Response<GuildUserPoints> response, Error error) {
        Request<GuildUserPoints> request = new Request<>(
                () -> restClient.putGuildUserPoints(guildId, userId, updateAction, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Updates a user's score in a guild.
     *
     * @param guildId      The guild ID.
     * @param userId       The user's ID.
     * @param updateAction The type of update action.
     * @param amount       The amount to update the user's score by.
     * @param response     The response callback.
     * @param error        The error callback.
     */
    public void updateGuildUserScore(long guildId, long userId, UpdateAction updateAction, int amount,
                                     Response<GuildUserScore> response, Error error) {
        Request<GuildUserScore> request = new Request<>(
                () -> restClient.putGuildUserScore(guildId, userId, updateAction, amount),
                response::onResponse,
                error::onError
        );
        asyncThreader.execute(request);
    }

    /**
     * Closes the Tatsumaki client by shutting down the executor services.
     */
    @Override
    public void close() {
        // Shutdown async threader and close rest client.
        asyncThreader.shutdown();
        restClient.close();
    }

}
