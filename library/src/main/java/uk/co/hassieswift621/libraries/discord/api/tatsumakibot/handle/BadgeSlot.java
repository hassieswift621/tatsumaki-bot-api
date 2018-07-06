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

/**
 * Created by Hassie on Friday, 06 July, 2018 - 11:16
 */
public class BadgeSlot {

    private final boolean occupied;
    private final int badgeSlotNo;
    private final String badgeName;
    private final String badgeImageURL;

    /**
     * Creates a badge slot which has no badge present.
     * @param badgeSlotNo The badge slot number
     */
    public BadgeSlot(int badgeSlotNo) {
        this.occupied = false;
        this.badgeSlotNo = badgeSlotNo;
        this.badgeName = null;
        this.badgeImageURL = null;
    }

    /**
     * Creates a badge slot which has a badge present.
     * @param badgeSlotNo The badge slot number
     * @param badgeName The name of the badge
     */
    public BadgeSlot(int badgeSlotNo, String badgeName) {
        this.occupied = true;
        this.badgeSlotNo = badgeSlotNo;
        this.badgeName = badgeName;
        badgeImageURL = "https://www.tatsumaki.xyz/images/badges/" + badgeName + ".png";
    }

    /**
     * Returns <code>true</code> if the badge slot has a badge
     * @return <code>true</code> if the badge slot has a badge; <code>false</code> otherwise
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Returns the badge slot number
     * @return The badge slot number
     */
    public int getSlotNo() {
        return badgeSlotNo;
    }

    /**
     * Returns the name of the badge which is present in this badge slot.
     * @return The name of the badge present in this badge slot
     */
    public String getName() {
        return badgeName;
    }

    /**
     * Returns the image URL of the badge which is present in this badge slot.
     * @return The image URL of the badge present in this badge slot
     */
    public String getImageURL() {
        return badgeImageURL;
    }
}
