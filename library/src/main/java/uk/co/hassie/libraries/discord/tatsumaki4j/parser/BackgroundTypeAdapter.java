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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import uk.co.hassie.libraries.discord.tatsumaki4j.handle.user.Background;
import uk.co.hassie.libraries.discord.tatsumaki4j.rest.Endpoint;

import java.io.IOException;

public class BackgroundTypeAdapter extends TypeAdapter<Background> {

    @Override
    public Background read(JsonReader in) throws IOException {
        // Get background URL.
        String imageUrl = in.nextString();

        // Remove URL to get background name.
        String name = imageUrl
                .replace(Endpoint.BACKGROUND_IMAGE, "")
                .replace(Endpoint.IMAGE_EXT, "");

        // Return background object.
        return new Background(imageUrl, name);
    }

    @Override
    public void write(JsonWriter out, Background value) throws IOException {

    }

}
