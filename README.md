Tatsumaki Bot API [![CircleCI](https://circleci.com/gh/hassieswift621/tatsumaki-bot-api.svg?style=svg)](https://circleci.com/gh/hassieswift621/tatsumaki-bot-api) [ ![Download](https://api.bintray.com/packages/hassieswift621/maven/tatsumaki-bot/images/download.svg) ](https://bintray.com/hassieswift621/maven/tatsumaki-bot/_latestVersion)
=================

An asynchronous Java API wrapper around one of the most popular bots on Discord, Tatsumaki.

Currently, the API allows you to fetch user profile data.

Note that I am in no way connected with the Tatsumaki Bot and their devs.
If you have any queries about the bot itself, please visit https://tatsumaki.xyz/

How do I get an API key
-----------------------
To get an API key, run the following command on Tatsumaki: **t!apikey**

Dependencies
------------
This library is available on JCenter. The current version is 0.1.0.

**Gradle Setup**
```gradle
implementation 'uk.co.hassieswift621.libraries.discord.api:tatsumaki-bot:0.1.0'
```

**Maven Setup**
```maven
<dependency>
  <groupId>uk.co.hassieswift621.libraries.discord.api</groupId>
  <artifactId>tatsumaki-bot</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

Tutorial
--------
```java
// Create the client.
TatsumakiClient tatsumakiClient = new ClientBuilder()
    .setAPIKey("YOUR TATSUMAKI BOT API KEY")
    .build();

// Request for user profile data for a user.
tatsumakiClient.getUser("User ID", new ResponseCallback() {
    @Override
    public void onSuccess(TatsumakiUser user) {
        // Request successful.
        // Do stuff with the data here.
        System.out.println("User's rank: " + user.getRank())
    }
    
    @Override
    public void onFailure(Throwable throwable) {
        // Error occurred. Handle error here.
        throwable.printStackTrace();
    }
});

// Do other stuff here if required while the above request
// is executed if required.
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
