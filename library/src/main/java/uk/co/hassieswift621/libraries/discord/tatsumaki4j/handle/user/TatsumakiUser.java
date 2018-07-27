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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user;

import java.util.Map;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:36.
 */
public class TatsumakiUser {

    private final String avatar;
    private final Background background;
    private final Map<Integer, BadgeSlot> badgeSlots;
    private final long credits;
    private final String infobox;
    private final long level;
    private final LevelProgress levelProgress;
    private final String name;
    private final long rank;
    private final long reputation;
    private final long userId;
    private final String title;
    private final long totalXp;

    public TatsumakiUser(String avatar, Background background, Map<Integer, BadgeSlot> badgeSlots, long credits,
                         String infobox, long level, LevelProgress levelProgress, String name, long rank,
                         long reputation, long userId, String title, long totalXp) {
        this.avatar = avatar;
        this.background = background;
        this.badgeSlots = badgeSlots;
        this.credits = credits;
        this.infobox = infobox;
        this.level = level;
        this.levelProgress = levelProgress;
        this.name = name;
        this.rank = rank;
        this.reputation = reputation;
        this.userId = userId;
        this.title = title;
        this.totalXp = totalXp;
    }

    public String getAvatar() {
        return avatar;
    }

    public Background getBackground() {
        return background;
    }

    public Map<Integer, BadgeSlot> getBadgeSlots() {
        return badgeSlots;
    }

    public long getCredits() {
        return credits;
    }

    public String getInfobox() {
        return infobox;
    }

    public long getLevel() {
        return level;
    }

    public LevelProgress getLevelProgress() {
        return levelProgress;
    }

    public String getName() {
        return name;
    }

    public long getRank() {
        return rank;
    }

    public long getReputation() {
        return reputation;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public long getTotalXp() {
        return totalXp;
    }

}