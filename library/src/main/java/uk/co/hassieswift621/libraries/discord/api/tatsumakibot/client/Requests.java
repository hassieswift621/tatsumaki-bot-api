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
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.guild.GuildLeaderboard;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.guild.GuildUserStats;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.user.TatsumakiUser;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.utils.Parser;
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
        return Parser.parseGuildLeaderboard(makeGetRequest(Endpoints.getGuildLeaderboard(guildId)));
    }

    public GuildLeaderboard getGuildLeaderboard(String guildId) throws TatsumakiException {
        return Parser.parseGuildLeaderboard(makeGetRequest(Endpoints.getGuildLeaderboard(guildId)));
    }

    public GuildUserStats getGuildUserStats(long guildId, long userId) throws TatsumakiException {
        return Parser.parseGuildUserStats(makeGetRequest(Endpoints.getGuildUserStats(guildId, userId)));
    }

    public GuildUserStats getGuildUserStats(String guildId, String userId) throws TatsumakiException {
        return Parser.parseGuildUserStats(makeGetRequest(Endpoints.getGuildUserStats(guildId, userId)));
    }

    public Ping getPing() throws TatsumakiException {
        return Parser.parsePing(makeGetRequest(Endpoints.getPing()));
    }

    public TatsumakiUser getUser(long userId) throws TatsumakiException {
        return Parser.parseUser(makeGetRequest(Endpoints.getUser(userId)));
    }

    public TatsumakiUser getUser(String userId) throws TatsumakiException {
        return Parser.parseUser(makeGetRequest(Endpoints.getUser(userId)));
    }

    private InputStream makeGetRequest(String getRequest) throws TatsumakiException {

        Request request = new Request.Builder()
                .get()
                .header("Authorization", token)
                .url(getRequest)
                .build();

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