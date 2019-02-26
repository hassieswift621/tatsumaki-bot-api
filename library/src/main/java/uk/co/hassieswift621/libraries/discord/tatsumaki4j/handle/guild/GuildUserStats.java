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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a user's stats in a guild.
 */
public class GuildUserStats {

    @SerializedName("guild_id")
    private long guildId;

    @SerializedName("points")
    private long points;

    @SerializedName("score")
    private long score;

    @SerializedName("user_id")
    private long userId;

    /**
     * @return The guild ID.
     */
    public long getGuildId() {
        return guildId;
    }

    /**
     * @return The user's points in the guild.
     */
    public long getPoints() {
        return points;
    }

    /**
     * @return The user's score in the guild.
     */
    public long getScore() {
        return score;
    }

    /**
     * @return The user's ID.
     */
    public long getUserId() {
        return userId;
    }

}
