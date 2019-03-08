Tatsumaki4J [![CircleCI](https://circleci.com/gh/hassieswift621/tatsumaki4j/tree/dev.svg?style=svg)](https://circleci.com/gh/hassieswift621/tatsumaki4j/tree/dev) [ ![Download](https://api.bintray.com/packages/hassieswift621/maven/tatsumaki4j/images/download.svg) ](https://bintray.com/hassieswift621/maven/tatsumaki4j/_latestVersion) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/d965dfc58838444cb98eb199bc04e31a)](https://www.codacy.com/app/hassieswift621/tatsumaki4j?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hassieswift621/tatsumaki4j&amp;utm_campaign=Badge_Grade)
=================

An asynchronous Java API wrapper around one of the most popular bots on Discord, Tatsumaki.

The API wrapper has all endpoints implemented: guilds, users and ping.

When you create the client, by default a number of fixed threads are created, specifically CPUs/CPU cores + 1.
You can customise this using the client builder.

The client is designed to run for the duration of your program, keeping the thread pool alive for quick async execution.

Once you are done with the client, call its close method to shutdown the executor services, which will keep your program alive.

You can also use the client with try with resources catch block if you plan to make one off requests, or if you wish to close the client after each request.

However, you must create a new instance of the client if you wish to make another API request after closing the client.

Note that I am not part of the Tatsumaki Bot development.
If you have any queries about the bot or the API, please visit https://tatsumaki.xyz/

How do I get an API key
-----------------------
To get an API key, run the following command on Tatsumaki: **t!apikey**

Dependencies
------------
This library is available on JCenter. The latest version is 0.6.0

Replace ``{LATEST_VERSION}`` with the latest version.

**Gradle Setup**
```gradle
implementation 'uk.co.hassie.libraries.discord.tatsumaki4j:{LATEST_VERSION}'
```

**Maven Setup**
```maven
<dependency>
  <groupId>uk.co.hassie.libraries.discord</groupId>
  <artifactId>tatsumaki4j</artifactId>
  <version>{LATEST_VERSION}</version>
  <type>pom</type>
</dependency>
```

Tutorial
--------
**Creating the client**
```java
// Create the client with the default number of threads.
TatsumakiClient tatsumakiClient = new TatsumakiClient("YOUR TATSUMAKI BOT TOKEN");

// Create the client with a custom number of threads.
TatsumakiClient tatsumakiClient = new TatsumakiClient.Builder()
    .setThreadPoolSize(1)
    .setToken("YOUR TATSUMAKI BOT TOKEN")
    .build();
```

**Retrieving user profile data**
```java
// Request for user profile data for a user.
tatsumakiClient.getUser("User ID",
    user -> {
        // Success, output some stuff.
        System.out.println("User's Rank: " + user.getRank());
        System.out.println("User's Reputation: " + user.getReputation());
    },
    // Output error message.
    Throwable::printStackTrace
}

// Do other stuff here if required while the above request
// is executed.
```

**Try with resources block**

**Other endpoints**
- GET Guild Leaderboard: ``getGuildLeaderboard(guildId, Response<GuildLeaderboard> response, Error error)``
- GET Guild User Stats: ``getGuildUserStats(guildId, userId, Response<GuildUserStats> response, Error error)``
- GET Ping: ``getPing(Response<Ping> response, Error error)``
- PUT Guild User Points: ``updateGuildUserPoints(guildId, userId, updateAction, amount, Response<GuildUserPoints> response, Error error)``
- PUT Guild User Score: ``updateGuildUserScore(guildId, userId, updateAction, amount, Response<GuildUserScore> response, Error error)``

**Closing down the client**
```java
tatsumakiClient.close();
```

License
-------
Copyright &copy;2018 HassieSwift621.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
