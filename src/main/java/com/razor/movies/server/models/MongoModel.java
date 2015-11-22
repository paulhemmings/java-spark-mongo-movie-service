package com.razor.movies.server.models;

public class MongoModel {

    @org.mongodb.morphia.annotations.Id
    @org.jongo.marshall.jackson.oid.Id
    @org.jongo.marshall.jackson.oid.ObjectId
    protected String id;

    public String getId() {
        return id;
    }

    public MongoModel setId(String id) {
        this.id = id;
        return this;
    }
}
