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

import java.util.Map;

/**
 * Created by Hassie on Saturday, 05 May, 2018 - 11:26.
 */
public interface TatsumakiUser {

    String getAvatar();

    String getBackground();

    Map<Byte, String> getBadges();

    long getCredits();

    String getInfobox();

    long getLevel();

    LevelProgress getLevelProgress();

    String getName();

    long getRank();

    long getReputation();

    String getTitle();

    long getTotalXP();
}