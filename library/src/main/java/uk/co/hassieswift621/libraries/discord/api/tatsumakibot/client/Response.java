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

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.client;

/**
<<<<<<< Updated upstream:library/src/main/java/uk/co/hassieswift621/libraries/discord/api/tatsumakibot/handle/LevelProgress.java
 * Created by Hassie on Saturday, 05 May, 2018 - 15:23.
 */
public class LevelProgress {

    private final long xpProgress;
    private final long xpRequired;

    public LevelProgress(long xpProgress, long xpRequired) {
        this.xpProgress = xpProgress;
        this.xpRequired = xpRequired;
    }

    public long getXPProgress() {
        return xpProgress;
    }

    public long getXPRequired() {
        return xpRequired;
    }
=======
 * Created by Hassie on Tuesday, 08 May, 2018 - 19:10.
 */
@FunctionalInterface
public interface Response<T> {

    void onResponse(T response);
>>>>>>> Stashed changes:library/src/main/java/uk/co/hassieswift621/libraries/discord/api/tatsumakibot/client/Response.java

}