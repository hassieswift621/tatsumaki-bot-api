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

package uk.co.hassie.libraries.discord.tatsumaki4j.sample;

import uk.co.hassie.libraries.discord.tatsumaki4j.client.TatsumakiClient;

public class Tatsumaki4JSample {

    public static void main(String[] args) {

        // Create client with default configuration.
        TatsumakiClient tatsumakiClient = new TatsumakiClient("YOUR TATSUMAKI BOT TOKEN");

        // Get user profile data.
        tatsumakiClient.getUser(
                Long.parseLong("USER ID"),
                user -> {
                    // Success, output some stuff.
                    System.out.println("User's Background URL: " + user.getBackground().getImageUrl());
                    System.out.println("User's Rank: " + user.getRank());
                    System.out.println("User's Reputation: " + user.getReputation());
                },
                // Output error message.
                Throwable::printStackTrace
        );

        // Close the client to release resources to allow the program to exit.
        tatsumakiClient.close();
    }

}
