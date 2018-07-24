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

<<<<<<< Updated upstream:library/src/main/java/uk/co/hassieswift621/libraries/discord/api/tatsumakibot/handle/TatsumakiUser.java
package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiJSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:36.
 */
public class TatsumakiUser {

    private String avatar;
    private Background background;
    private Map<Integer, BadgeSlot> badgeSlots;
    private long credits;
    private String infobox;
    private long level;
    private LevelProgress levelProgress;
    private String name;
    private long rank;
    private long reputation;
    private String title;
    private long totalXP;

    public TatsumakiUser(JSONObject json) throws TatsumakiJSONException {

        try {

            avatar = json.getString("avatar_url");
            background = new Background(json.getString("background"));
            credits = json.getLong("credits");
            infobox = json.optString("info_box");
            name = json.getString("name");
            level = json.getLong("level");
            levelProgress = new LevelProgress(
                    json.getJSONArray("xp").getLong(0),
                    json.getJSONArray("xp").getLong(1)
            );
            rank = json.getLong("rank");
            reputation = json.getLong("reputation");
            title = json.optString("title");
            totalXP = json.getLong("total_xp");

            // Run through badges array and add to map.
            badgeSlots = new HashMap<>();
            JSONArray badgesArray = json.getJSONArray("badgeSlots");
            for (int i = 0; i < badgesArray.length(); i++) {

                int badgeSlotNo = i + 1;

                String badgeName = badgesArray.optString(i, null);


                if (badgeName == null) {
                    badgeSlots.put(i + 1, new BadgeSlot(badgeSlotNo));
                } else {
                    badgeSlots.put(i + 1, new BadgeSlot(badgeSlotNo, badgeName));
                }
            }

        } catch (JSONException e) {
            throw new TatsumakiJSONException("Tatsumaki Bot API JSON Exception - Failed to extract JSON", e);
        }

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

    public String getTitle() {
        return title;
    }

    public long getTotalXP() {
        return totalXP;
=======
package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.user;

/**
 * Created by Hassie on Friday, 06 July, 2018 - 11:18
 */
public class Background {

    private final String imageUrl;
    private final String name;

    public Background(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
>>>>>>> Stashed changes:library/src/main/java/uk/co/hassieswift621/libraries/discord/api/tatsumakibot/handle/user/Background.java
    }

}