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
 * Created by Hassie on Friday, 06 July, 2018 - 11:18
 */
public class Background {

    private final String backgroundName;
    private final String backgroundURL;

    public Background(String backgroundName) {
        this.backgroundName = backgroundName;
        this.backgroundURL = "https://www.tatsumaki.xyz/images/backgrounds/profile/" + backgroundName + ".png";
    }

    public String getName() {
        return backgroundName;
    }

    public String getImageURL() {
        return backgroundURL;
    }

}