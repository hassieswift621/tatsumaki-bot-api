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

package uk.co.hassie.libraries.discord.tatsumaki4j.handle.user;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class TatsumakiUser {

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("background")
    private Background background;

    @SerializedName("badgeSlots")
    private List<BadgeSlot> badgeSlots;

    @SerializedName("credits")
    private long credits;

    @SerializedName("info_box")
    private String infobox;

    @SerializedName("level")
    private long level;

    @SerializedName("xp")
    private LevelProgress levelProgress;

    @SerializedName("name")
    private String name;

    @SerializedName("rank")
    private long rank;

    @SerializedName("reputation")
    private long reputation;

    @SerializedName("title")
    private String title;

    @SerializedName("total_xp")
    private long totalXp;

    /**
     * @return The user's avatar.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @return The user's profile background.
     */
    public Background getBackground() {
        return background;
    }

    /**
     * @return The user's profile badges.
     */
    public List<BadgeSlot> getBadgeSlots() {
        return Collections.unmodifiableList(badgeSlots);
    }

    /**
     * @return The user's credits.
     */
    public long getCredits() {
        return credits;
    }

    /**
     * @return The user's profile infobox.
     */
    public String getInfobox() {
        return infobox;
    }

    /**
     * @return The user's level.
     */
    public long getLevel() {
        return level;
    }

    /**
     * @return The user's level progress.
     */
    public LevelProgress getLevelProgress() {
        return levelProgress;
    }

    /**
     * @return The user's Discord username.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The user's rank.
     */
    public long getRank() {
        return rank;
    }

    /**
     * @return The user's reputation.
     */
    public long getReputation() {
        return reputation;
    }

    /**
     * @return The user's profile title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The user's total XP.
     */
    public long getTotalXp() {
        return totalXp;
    }

}
