package com.razor.movies.server;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.razor.movies.server.configuration.PersistenceConfig;
import com.razor.movies.server.models.MovieModel;
import com.razor.movies.server.providers.ModelProvider;
import com.razor.movies.server.providers.ProviderFactory;
import com.razor.movies.server.resources.MovieResource;
import com.razor.movies.server.services.MovieService;
import org.aeonbits.owner.ConfigFactory;
import spark.utils.StringUtils;

import java.util.ArrayList;

import static spark.SparkBase.staticFileLocation;

public class EntryPoint {

    /**
     * Hit it!
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception{

        staticFileLocation("/public");
        PersistenceConfig persistenceConfig = buildPersistenceConfig();
        MongoClient client = buildClient(persistenceConfig);

        ModelProvider<MovieModel> modelProvider = new ProviderFactory<MovieModel>()
                .buildProvider(persistenceConfig, client, persistenceConfig.mongoDatabase(), "movies", MovieModel.class);

        new MovieResource().setMovieService(new MovieService().setModelProvider(modelProvider));
    }

    private static MongoClient buildClient(PersistenceConfig persistenceConfig) {

        if (StringUtils.isEmpty(persistenceConfig.mongoUser())) {
            return new MongoClient(persistenceConfig.mongoHost());
        }

        ArrayList<ServerAddress> serverAddresses
                = Lists.newArrayList(new ServerAddress(persistenceConfig.mongoHost()));

        ArrayList<MongoCredential> mongoCredentials
                = Lists.newArrayList(MongoCredential.createCredential(persistenceConfig.mongoUser(), persistenceConfig.mongoDatabase(), persistenceConfig.mongoPassword().toCharArray()));

        return new MongoClient(serverAddresses, mongoCredentials);
    }

    /**
     * Get configuration for persistence layer
     * @return
     */

    private static PersistenceConfig buildPersistenceConfig() {
        return ConfigFactory.create(PersistenceConfig.class);
    }
}
