package com.razor.movies.server.configuration;

import org.aeonbits.owner.Config;

public interface PersistenceConfig extends Config {

    @Config.Key("persistence.mongo.host")
    @DefaultValue("localhost")
    String mongoHost();

    @Config.Key("persistence.mongo.database")
    @DefaultValue("movies")
    String mongoDatabase();

    @Config.Key("persistence.mongo.provider")
    @DefaultValue("jongo")
    String mongoProvider();

    @Config.Key("persistence.mongo.username")
    @DefaultValue("")
    String mongoUser();

    @Config.Key("persistence.mongo.password")
    @DefaultValue("")
    String mongoPassword();
}
