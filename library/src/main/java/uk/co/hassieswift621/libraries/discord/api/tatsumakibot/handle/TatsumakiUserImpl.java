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

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle;

import org.json.JSONException;
import org.json.JSONObject;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.exceptions.TatsumakiJSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:36.
 */
public class TatsumakiUserImpl implements TatsumakiUser {

    private String avatar;
    private String background;
    private Map<Byte, String> badges;
    private long credits;
    private String infobox;
    private long level;
    private LevelProgress levelProgress;
    private String name;
    private long rank;
    private long reputation;
    private String title;
    private long totalXP;

    public TatsumakiUserImpl(JSONObject json) throws TatsumakiJSONException {

        System.out.println(json);
        try {

            avatar = json.getString("avatar_url");
            background = json.optString("background", "Default");
            credits = json.getLong("credits");
            infobox = json.optString("info_box");
            name = json.getString("name");
            level = json.getLong("level");
            levelProgress = new LevelProgressImpl(json.getJSONArray("xp").getLong(0),
                    json.getJSONArray("xp").getLong(1));
            rank = json.getLong("rank");
            reputation = json.getLong("reputation");
            title = json.optString("title");
            totalXP = json.getLong("total_xp");

            // Run through badges array and add to map.
            badges = new HashMap<>();
            for (int i = 0; i < json.getJSONArray("badgeSlots").length(); i++) {
                String badgeName = json.getJSONArray("badgeSlots").get(i).toString();
                if (badgeName.equals("null")) {
                    badgeName = "empty";
                }

                badges.put((byte) (i + 1), badgeName);
            }

        } catch (JSONException e) {
            throw new TatsumakiJSONException("Tatsumaki Bot API JSON Exception - Failed to extract JSON", e);
        }

    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getBackground() {
        return background;
    }

    @Override
    public Map<Byte, String> getBadges() {
        return badges;
    }

    @Override
    public long getCredits() {
        return credits;
    }

    @Override
    public String getInfobox() {
        return infobox;
    }

    @Override
    public long getLevel() {
        return level;
    }

    @Override
    public LevelProgress getLevelProgress() {
        return levelProgress;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getRank() {
        return rank;
    }

    @Override
    public long getReputation() {
        return reputation;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public long getTotalXP() {
        return totalXP;
    }
}