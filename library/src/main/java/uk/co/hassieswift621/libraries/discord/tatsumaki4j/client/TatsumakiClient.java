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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.client;

import uk.co.hassieswift621.libraries.asyncthreader.AsyncThreader;
import uk.co.hassieswift621.libraries.asyncthreader.Request;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildRankedUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildUserStats;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.rest.RestClient;

import java.util.List;

public class TatsumakiClient {

    private final AsyncThreader asyncThreader;
    private final RestClient restClient;

    public TatsumakiClient(String token) {
        asyncThreader = new AsyncThreader();
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

    public void shutdown() {
        asyncThreader.shutdown();
    }

}
