package com.razor.movies.server.providers;

import com.mongodb.MongoClient;
import com.razor.movies.server.configuration.PersistenceConfig;
import com.razor.movies.server.models.MongoModel;
import com.razor.movies.server.providers.jongo.JongoProvider;
import com.razor.movies.server.providers.mongo.MongoProvider;
import com.razor.movies.server.providers.mongo.SimpleMongoModelAdapter;
import com.razor.movies.server.providers.morphia.MorphiaProvider;

public class ProviderFactory<T extends MongoModel> {

    /*
     * Build provider based on configuration
     */

    public ModelProvider<T> buildProvider(PersistenceConfig persistenceConfig,
                                                          MongoClient client,
                                                          String database,
                                                          String collection,
                                                          Class<T> clazz) throws Exception {
        ModelProvider provider;
        switch (persistenceConfig.mongoProvider()) {
            case "mongo":
                provider = buildMongoProvider(client, database, collection, clazz);
                break;
            case "jongo":
                provider = buildJongoProvider(client, database, collection, clazz);
                break;
            case "morphia":
                provider = buildMorphiaProvider(client, database, clazz);
                break;
            default:
                throw new Exception("Invalid provider configuration");
        }
        return provider;
    }

    /**
     * Use the Jongo ORM
     * @param client
     * @return
     */

    private ModelProvider<T> buildJongoProvider(MongoClient client, String database, String collection, Class<T> clazz) {
        return new JongoProvider<>(client.getDB(database), collection, clazz);
    }

    /**
     * Use the standard OOTB Mongo provider
     * @param client
     * @return
     */

    private ModelProvider<T> buildMongoProvider(MongoClient client, String database, String collection, Class<T> clazz ) {
        return new MongoProvider<>(client.getDatabase(database).getCollection(collection), new SimpleMongoModelAdapter<>(clazz));
    }

    /**
     * Use Morphia ORM
     * @param client
     * @param database
     * @param clazz
     * @return
     */

    private ModelProvider<T> buildMorphiaProvider(MongoClient client, String database, Class<T> clazz) {
        return new MorphiaProvider<>(client, database, clazz);
    }
}
