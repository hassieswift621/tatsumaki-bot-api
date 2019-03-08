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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.Badge;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.BadgeSlot;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.rest.Endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BadgeTypeAdapter extends TypeAdapter<List<BadgeSlot>> {

    @Override
    public List<BadgeSlot> read(JsonReader in) throws IOException {
        // Create list.
        List<BadgeSlot> badgeSlots = new ArrayList<>();

        // Begin array read.
        in.beginArray();

        // Run through array and add badge slots to list.
        int i = 0;
        while (in.hasNext()) {
            // Increment badge slot no.
            i++;

            // Check if badge is null.
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                badgeSlots.add(new BadgeSlot(null, i));
            } else {
                // Get badge name.
                String badgeName = in.nextString();

                // Check if the badge starts with flag_ or gameico_
                // so we can set the image URL to be null.
                // These badges don't have accessible image URLs at the moment.
                if (badgeName.startsWith("gameico_") || badgeName.startsWith("flag_")) {
                    badgeSlots.add(new BadgeSlot(new Badge(null, badgeName), i));
                } else {
                    badgeSlots.add(new BadgeSlot(
                            new Badge(Endpoint.getBadgeImage(badgeName), badgeName), i));
                }
            }
        }

        // End array read.
        in.endArray();

        return badgeSlots;
    }

    @Override
    public void write(JsonWriter out, List<BadgeSlot> value) throws IOException {

    }

}
