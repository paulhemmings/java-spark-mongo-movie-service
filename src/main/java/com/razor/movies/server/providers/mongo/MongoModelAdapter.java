package com.razor.movies.server.providers.mongo;

import org.bson.Document;

public interface MongoModelAdapter<T> {
    T fromDocument(Document document);
    Document toDocument(T model);
}
