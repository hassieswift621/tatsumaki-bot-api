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

package uk.co.hassieswift621.libraries.discord.tatsumaki4j.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import uk.co.hassieswift621.libraries.discord.tatsumaki4j.handle.user.LevelProgress;

import java.io.IOException;

public class LevelProgressTypeAdapter extends TypeAdapter<LevelProgress> {

    @Override
    public LevelProgress read(JsonReader in) throws IOException {
        // Begin array read.
        in.beginArray();

        // Create level progress object.
        LevelProgress levelProgress = new LevelProgress(in.nextLong(), in.nextLong());

        // End array read.
        in.endArray();

        return levelProgress;
    }

    @Override
    public void write(JsonWriter out, LevelProgress value) throws IOException {

    }

}
