package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.client;

import uk.co.hassieswift621.libraries.discord.api.tatsumakibot.handle.TatsumakiUser;

/**
 * Created by Hassie on Tuesday, 08 May, 2018 - 19:10.
 */
@FunctionalInterface
public interface IResponse {

    void onResponse(TatsumakiUser user);

}