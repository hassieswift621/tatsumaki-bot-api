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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.ping;

import java.time.Instant;

/**
 * Represents a ping response.
 */
public class Ping {

    private final String message;
    private final Instant timestamp;

    public Ping(String message, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return The ping receive timestamp.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

}
