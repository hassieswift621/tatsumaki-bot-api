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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.rest;

/**
 * Tatsumaki API endpoints.
 */
public class Endpoint {

    private static final String BASE_URL = "https://api.tatsumaki.xyz/";

    public static final String BACKGROUND_IMAGE = "https://www.tatsumaki.xyz/images/backgrounds/profile/";
    private static final String BADGE_IMAGE = "https://www.tatsumaki.xyz/images/badges/";
    public static final String IMAGE_EXT = ".png";

    private static final String GUILDS = "guilds/";
    private static final String PING = "ping";
    private static final String USERS = "users/";

    public static String getBackgroundImage(String backgroundName) {
        return BACKGROUND_IMAGE + backgroundName + IMAGE_EXT;
    }

    public static String getBadgeImage(String badgeName) {
        return BADGE_IMAGE + badgeName + IMAGE_EXT;
    }

    public static String getGuildLeaderboard(long guildId) {
        return BASE_URL + GUILDS + guildId + "/leaderboard";
    }

    public static String getGuildUserStats(long guildId, long userId) {
        return BASE_URL + GUILDS + guildId + "/members/" + userId + "/stats";
    }

    public static String getPing() {
        return BASE_URL + PING;
    }

    public static String getUser(long userId) {
        return BASE_URL + USERS + userId;
    }

    public static String putGuildUserPoints(long guildId, long userId) {
        return BASE_URL + GUILDS + guildId + "/members/" + userId + "/points";
    }

    public static String putGuildUserScore(long guildId, long userId) {
        return BASE_URL + GUILDS + guildId + "/members/" + userId + "/score";
    }

}