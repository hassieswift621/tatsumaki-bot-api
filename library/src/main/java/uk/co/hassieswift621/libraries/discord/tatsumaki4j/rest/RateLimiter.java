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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.rest;

/**
 * Tatsumaki API rate limiter.
 * The rate limit of the API is 1 request every 0.25s.
 */
class RateLimiter {

    private long lastRequest; // The last API request time.
    private final int resetInterval; // The interval after which the rate limit resets.

    public RateLimiter() {
        // We'll set the reset interval to 0.3s, in case there are some inconsistencies.
        resetInterval = 300;

        // Set last request to now - reset interval to allow an instant initial request.
        lastRequest = System.currentTimeMillis();
    }

    public void acquire() throws InterruptedException {
        synchronized (this) {
            // Get current time.
            long currentTime = System.currentTimeMillis();

            // Check if the current time is greater than the rate limit reset time.
            if (currentTime >= lastRequest + resetInterval) {
                // Allow request.
                lastRequest = currentTime;
                return;
            }

            // Sleep for the time difference.
            Thread.sleep((lastRequest + resetInterval) - currentTime);

            // Allow request.
            lastRequest = System.currentTimeMillis();
        }
    }

}
