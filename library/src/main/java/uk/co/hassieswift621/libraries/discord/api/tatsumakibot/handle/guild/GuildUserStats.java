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

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.guild;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 20:42
 */
public class GuildUserStats {

    private final long guildId;
    private final long points;
    private final long score;
    private final long userId;

    public GuildUserStats(long guildId, long points, long score, long userId) {
        this.guildId = guildId;
        this.points = points;
        this.score = score;
        this.userId = userId;
    }

    public long getGuildId() {
        return guildId;
    }

    public long getPoints() {
        return points;
    }

    public long getScore() {
        return score;
    }

    public long getUserId() {
        return userId;
    }

}
