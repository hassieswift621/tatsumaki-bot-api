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

/**
 * Created by Hassie on Tuesday, 24 July, 2018 - 12:53
 */
public class GuildRankedUser {

    private final long userId;
    private final long rank;
    private final long score;

    public GuildRankedUser(long userId, long rank, long score) {
        this.userId = userId;
        this.rank = rank;
        this.score = score;
    }

    public long getUserId() {
        return userId;
    }

    public long getRank() {
        return rank;
    }

    public long getScore() {
        return score;
    }

}