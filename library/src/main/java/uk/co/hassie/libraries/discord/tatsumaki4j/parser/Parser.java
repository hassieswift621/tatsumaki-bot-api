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

package uk.co.hassie.libraries.discord.tatsumaki4j.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.ping.Ping;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.Background;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.BadgeSlot;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.LevelProgress;

import java.lang.reflect.Type;
import java.util.List;

/**
 * JSON parser.
 */
public class Parser {

    private final Gson gson;

    public Parser() {
        // Create GSON.
        gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Background.class, new BackgroundTypeAdapter())
                .registerTypeAdapter(new TypeToken<List<BadgeSlot>>() {
                }.getType(), new BadgeTypeAdapter())
                .registerTypeAdapter(LevelProgress.class, new LevelProgressTypeAdapter())
                .registerTypeAdapter(Ping.class, new PingTypeAdapter())
                .create();
    }

    public <T> T parse(String json, Class<T> type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

    public <T> T parse(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

}
