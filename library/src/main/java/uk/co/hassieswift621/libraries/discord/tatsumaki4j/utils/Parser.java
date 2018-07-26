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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.client.Endpoints;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.exceptions.TatsumakiException;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.guild.*;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.user.*;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild.*;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.*;
import uk.co.hassieswift621.libraries.jsonio.JsonIO;
import uk.co.hassieswift621.libraries.jsonio.exceptions.JsonIOException;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 11:43
 */
public class Parser {

    public static String createGuildUserPointsRequest(GuildUpdateAction action, int amount) throws TatsumakiException {

        if (amount < 1 || amount > 50000)
            throw new TatsumakiException("Failed to create guild user score response body: The amount of points to adjust must be between 0 (exclusive) and 50,000 (inclusive)");

        try {

            JSONObject json = new JSONObject();
            json.put("amount", amount).put("action", action.getAction());

            return json.toString();

        } catch (JSONException e) {
            throw new TatsumakiException("Failed to create guild user points response body", e);
        }
    }

    public static String createGuildUserScoreRequest(GuildUpdateAction action, int amount) throws TatsumakiException {

        if (amount < 1 || amount > 50000)
            throw new TatsumakiException("Failed to create guild user score response body: The amount of score to adjust must be between 0 (exclusive) and 50,000 (inclusive)");

        try {

            JSONObject json = new JSONObject();
            json.put("amount", amount).put("action", action.getAction());

            return json.toString();

        } catch (JSONException e) {
            throw new TatsumakiException("Failed to create guild user score response body", e);
        }
    }

    public static Long parseGuildId(String guildId) throws TatsumakiException {

        try {

            return Long.parseUnsignedLong(guildId);

        } catch (NumberFormatException e) {
            throw new TatsumakiException("Guild ID is not valid", e);
        }
    }

    public static GuildLeaderboard parseGuildLeaderboard(InputStream response, long guildId) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONArray json = JsonIO.toJsonArray(inputStream);

            // Filter out nulls.
            json = new JSONArray(json.toList().stream().filter(Objects::nonNull).collect(Collectors.toList()));

            // List to store guild ranked users.
            List<GuildRankedUser> guildRankedUsers = new ArrayList<>();

            // Run through array and add to list.
            for (int i = 0; i < json.length(); i++) {

                JSONObject user = json.getJSONObject(i);

                long userId = Long.parseUnsignedLong(user.getString("user_id"));
                long rank = user.getLong("rank");
                long score = Long.parseUnsignedLong(user.getString("score"));

                guildRankedUsers.add(new GuildRankedUser(userId, rank, score));
            }

            return new GuildLeaderboard(guildId, Collections.unmodifiableList(guildRankedUsers));

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse guild leaderboard response", e);
        }
    }

    public static GuildUserPoints parseGuildUserPoints(InputStream response, long guildId, long userId) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONObject json = JsonIO.toJson(inputStream);

            long points = Long.parseUnsignedLong(json.getString("points"));

            return new GuildUserPoints(guildId, points, userId);

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse updated guild user points response", e);
        }
    }

    public static GuildUserScore parseGuildUserScore(InputStream response, long guildId, long userId) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONObject json = JsonIO.toJson(inputStream);

            long score = Long.parseUnsignedLong(json.getString("score"));

            return new GuildUserScore(guildId, score, userId);

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse updated guild user score response", e);
        }
    }

    public static GuildUserStats parseGuildUserStats(InputStream response) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONObject json = JsonIO.toJson(inputStream);

            long guildId = Long.parseUnsignedLong(json.getString("guild_id"));
            long points = Long.parseUnsignedLong(json.getString("points"));
            long score = Long.parseUnsignedLong(json.getString("score"));
            long userId = Long.parseUnsignedLong(json.getString("user_id"));

            return new GuildUserStats(guildId, points, score, userId);

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse guild user stats response", e);
        }
    }

    public static Ping parsePing(InputStream response) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONObject json = JsonIO.toJson(inputStream);

            String message = json.getString("message");
            Instant time = Instant.ofEpochMilli(json.getLong("time"));

            return new Ping(message, time);

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse ping response", e);
        }
    }

    public static TatsumakiUser parseUser(InputStream response, long userId) throws TatsumakiException {

        try (InputStream inputStream = response) {

            JSONObject json = JsonIO.toJson(inputStream);

            String avatar = json.getString("avatar_url");
            long credits = json.getLong("credits");
            String infobox = json.optString("info_box");
            String name = json.getString("name");
            long level = json.getLong("level");
            long rank = json.getLong("rank");
            long reputation = json.getLong("reputation");
            String title = json.optString("title");
            long totalXP = json.getLong("total_xp");

            // Background.
            String backgroundName = json.getString("background");
            String backgroundUrl = Endpoints.getBackgroundImage(backgroundName);
            Background background = new Background(backgroundName, backgroundUrl);

            // Level progress.
            LevelProgress levelProgress = new LevelProgress(json.getJSONArray("xp").getLong(0),
                    json.getJSONArray("xp").getLong(1));

            // Badges.
            // Run through badges array and add to map.
            Map<Integer, BadgeSlot> badgeSlots = new HashMap<>();
            JSONArray badgesArray = json.getJSONArray("badgeSlots");
            for (int i = 0; i < badgesArray.length(); i++) {

                int slotNo = i + 1;
                String badgeName = badgesArray.optString(i, null);

                if (badgeName == null) {
                    badgeSlots.put(i + 1, new BadgeSlot(null, slotNo));
                } else {
                    badgeSlots.put(i + 1, new BadgeSlot(new Badge(Endpoints.getBadgeImage(badgeName), badgeName),
                            slotNo));
                }
            }

            return new TatsumakiUser(avatar, background, Collections.unmodifiableMap(badgeSlots), credits, infobox, level,
                    levelProgress, name, rank, reputation, userId, title, totalXP);

        } catch (IOException | JSONException | JsonIOException e) {
            throw new TatsumakiException("Failed to parse user response", e);
        }
    }

    public static Long parseUserId(String userId) throws TatsumakiException {

        try {

            return Long.parseUnsignedLong(userId);

        } catch (NumberFormatException e) {
            throw new TatsumakiException("User ID is not valid", e);
        }
    }

}