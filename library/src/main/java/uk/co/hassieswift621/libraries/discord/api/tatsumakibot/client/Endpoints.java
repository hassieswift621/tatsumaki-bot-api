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

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 11:58
 */
public class Endpoints {

    private static final String BASE_URL = "https://api.tatsumaki.xyz/";

    private static final String BACKGROUND_IMAGE = "https://www.tatsumaki.xyz/images/backgrounds/profile/";
    private static final String BADGE_IMAGE = "https://www.tatsumaki.xyz/images/badges/";

    private static final String GUILDS = "guilds/";
    private static final String USERS = "users/";

    public static String getBackgroundImage(String backgroundName) {
        return BACKGROUND_IMAGE + backgroundName + ".png";
    }

    public static String getBadgeImage(String badgeName) {
        return BADGE_IMAGE + badgeName + ".png";
    }

    public static String getGuildLeaderboard(long guildId) {
        return BASE_URL + GUILDS + guildId + "/leaderboard";
    }

    public static String getGuildLeaderboard(String guildId) {
        return BASE_URL + GUILDS + guildId + "/leaderboard";
    }

    public static String getGuildUserStats(long guildId, long userId) {
        return BASE_URL + GUILDS + guildId + "/members/" + userId + "/stats";
    }

    public static String getGuildUserStats(String guildId, String userId) {
        return BASE_URL + GUILDS + guildId + "/members/" + userId + "/stats";
    }

    public static String getUser(long userId) {
        return BASE_URL + USERS + userId;
    }

    public static String getUser(String userId) {
        return BASE_URL + USERS + userId;
    }

}
