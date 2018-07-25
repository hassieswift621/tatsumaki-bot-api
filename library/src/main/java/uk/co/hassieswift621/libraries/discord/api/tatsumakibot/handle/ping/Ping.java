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

package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.ping;

import java.time.Instant;

/**
 * Created by Hassie on Wednesday, 25 July, 2018 - 11:38
 */
public class Ping {

    private final String message;
    private final Instant time;

    public Ping(String message, Instant time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTime() {
        return time;
    }

}
