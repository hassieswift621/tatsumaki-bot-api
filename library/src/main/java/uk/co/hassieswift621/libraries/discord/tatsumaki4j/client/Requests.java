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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.client;

import okhttp3.*;
import okhttp3.Response;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.exceptions.TatsumakiException;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildLeaderboard;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildUserPoints;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildUserScore;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.GuildUserStats;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.utils.GuildUpdateAction;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.utils.Parser;
import uk.co.hassieswift621.libraries.jsonio.JsonIO;
import uk.co.hassieswift621.libraries.jsonio.exceptions.JsonIOException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 11:23
 */
public class Requests {

    private final OkHttpClient httpClient;
    private final String token;

    public Requests(OkHttpClient httpClient, String token) {
        this.httpClient = httpClient;
        this.token = token;
    }

    public GuildLeaderboard getGuildLeaderboard(long guildId) throws TatsumakiException {
        return Parser.parseGuildLeaderboard(makeGetRequest(Endpoints.getGuildLeaderboard(guildId)), guildId);
    }

    public GuildLeaderboard getGuildLeaderboard(String guildId) throws TatsumakiException {
        return getGuildLeaderboard(Parser.parseGuildId(guildId));
    }

    public GuildUserStats getGuildUserStats(long guildId, long userId) throws TatsumakiException {
        return Parser.parseGuildUserStats(makeGetRequest(Endpoints.getGuildUserStats(guildId, userId)));
    }

    public GuildUserStats getGuildUserStats(String guildId, String userId) throws TatsumakiException {
        return getGuildUserStats(Parser.parseGuildId(guildId), Parser.parseUserId(userId));
    }

    public Ping getPing() throws TatsumakiException {
        return Parser.parsePing(makeGetRequest(Endpoints.getPing()));
    }

    public TatsumakiUser getUser(long userId) throws TatsumakiException {
        return Parser.parseUser(makeGetRequest(Endpoints.getUser(userId)), userId);
    }

    public TatsumakiUser getUser(String userId) throws TatsumakiException {
        return getUser(Parser.parseUserId(userId));
    }

    public GuildUserPoints updateGuildUserPoints(long guildId, long userId, GuildUpdateAction action,
                                                 int amount) throws TatsumakiException {
        return Parser.parseGuildUserPoints(makePutRequest(
                Endpoints.putGuildUserPoints(guildId, userId),
                Parser.createGuildUserPointsRequest(action, amount)), guildId, userId);
    }

    public GuildUserPoints updateGuildUserPoints(String guildId, String userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return updateGuildUserPoints(Parser.parseGuildId(guildId), Parser.parseUserId(userId), action, amount);
    }

    public GuildUserScore updateGuildUserScore(long guildId, long userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return Parser.parseGuildUserScore(makePutRequest(
                Endpoints.putGuildUserScore(guildId, userId),
                Parser.createGuildUserScoreRequest(action, amount)), guildId, userId);
    }

    public GuildUserScore updateGuildUserScore(String guildId, String userId, GuildUpdateAction action,
                                               int amount) throws TatsumakiException {
        return updateGuildUserScore(Parser.parseGuildId(guildId), Parser.parseUserId(userId), action, amount);
    }

    private InputStream makeGetRequest(String url) throws TatsumakiException {

        Request request = new Request.Builder()
                .get()
                .header("Authorization", token)
                .url(url)
                .build();

        return getResponse(request);
    }

    private InputStream makePutRequest(String url, String json) throws TatsumakiException {

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .put(requestBody)
                .header("Authorization", token)
                .url(url)
                .build();

        return getResponse(request);
    }

    private InputStream getResponse(Request request) throws TatsumakiException {

        try {

            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                return response.body().byteStream();
            }

            // Parse error.
            if (response.body() != null) {

                JSONObject error = JsonIO.toJson(response.body().byteStream());
                throw new TatsumakiException(error.getString("message"));
            }

            throw new TatsumakiException("Failed to get response");

        } catch (IOException | JsonIOException e) {
            throw new TatsumakiException("Failed to get response", e);
        }
    }

}