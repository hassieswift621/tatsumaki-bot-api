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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.guild;

import java.util.List;

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 12:53
 */
public class GuildLeaderboard {

    private final long guildId;
    private final List<GuildRankedUser> rankedUsers;

    public GuildLeaderboard(long guildId, List<GuildRankedUser> rankedUsers) {
        this.guildId = guildId;
        this.rankedUsers = rankedUsers;
    }

    public long getGuildId() {
        return guildId;
    }

    public List<GuildRankedUser> getRankedUsers() {
        return rankedUsers;
    }

}