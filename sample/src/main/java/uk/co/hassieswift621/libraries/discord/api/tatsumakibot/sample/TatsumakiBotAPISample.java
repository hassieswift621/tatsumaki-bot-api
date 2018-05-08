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

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.sample;

import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.client.*;
import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.TatsumakiUser;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 15:47.
 */
public class TatsumakiBotAPISample {

    public static void main(String[] args) {

        // Create client.
        TatsumakiClient tatsumakiClient = new ClientBuilder()
                .setAPIKey("YOUR TATSUMAKI BOT API KEY")
                .build();

        // Get user profile data.
        tatsumakiClient.getUser("USER ID",
                user -> {
                    // Success, output some stuff.
                    System.out.println("User's Rank: " + user.getRank());
                    System.out.println("User's Reputation: " + user.getReputation());
                },
                // Output error message.
                Throwable::printStackTrace
        );
    }
}