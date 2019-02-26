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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user;

import java.util.Optional;

/**
 * Represents a user profile badge slot, containing a badge.
 */
public class BadgeSlot {

    private final Badge badge;
    private final int slotNo;

    public BadgeSlot(Badge badge, int slotNo) {
        this.badge = badge;
        this.slotNo = slotNo;
    }

    /**
     * @return A badge if the slot is not empty.
     */
    public Optional<Badge> getBadge() {
        return Optional.ofNullable(badge);
    }

    /**
     * @return The slot number for this badge slot.
     */
    public int getSlotNo() {
        return slotNo;
    }

}