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

/**
 * Represents a user profile background.
 */
public class Background {

    private final String imageUrl;
    private final String name;

    public Background(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    /**
     * @return The background image URL.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @return The name of the background.
     */
    public String getName() {
        return name;
    }

}
