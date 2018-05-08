package uk.co.hassieswift621.libraries.discord.api.tatsumakibot.client;

/**
 * Created by Hassie on Tuesday, 08 May, 2018 - 19:13.
 */
@FunctionalInterface
public interface IError {

    void onError(Throwable throwable);

}
