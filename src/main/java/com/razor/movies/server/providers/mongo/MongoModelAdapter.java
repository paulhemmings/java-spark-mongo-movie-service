package com.razor.movies.server.providers.mongo;

import org.bson.Document;

public interface MongoModelAdapter<T> {
    public T fromDocument(Document document);
    public Document toDocument(T model);
}
