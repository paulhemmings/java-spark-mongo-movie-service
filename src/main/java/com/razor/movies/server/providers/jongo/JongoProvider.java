package com.razor.movies.server.providers.jongo;

import com.mongodb.DB;
import com.razor.movies.server.providers.ModelProvider;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.ArrayList;
import java.util.List;

public class JongoProvider<T> implements ModelProvider<T> {

    private MongoCollection collection;
    private Class<T> clazz;

    public JongoProvider(DB db, String collection, Class<T> clazz) {
        this.clazz = clazz;
        Jongo jongo = new Jongo(db);
        this.collection = jongo.getCollection(collection);
    }

    public List<T> findAll() {
        List<T> models = new ArrayList<>();
        MongoCursor<T> cursor = this.collection.find().as(this.clazz);
        while (cursor.hasNext()) {
            T model = cursor.next();
            models.add(model);
        }
        return models;
    }

    public T findById(String id) {
        return this.collection.findOne(new ObjectId(id)).as(this.clazz);
    }

    @Override
    public T update(T model, String id) {
        collection.save(model);
        return model;
    }

    public List<T> find(String key, String value) {
        List<T> models = new ArrayList<>();
        MongoCursor<T> cursor = this.collection.find("{'" + key + "' : " + "'" + value + "'}").as(this.clazz);
        if (cursor.hasNext()) {
            T model = cursor.next();
            models.add(model);
        }
        return models;
    }
}
