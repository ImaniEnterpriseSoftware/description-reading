package com.example.descriptionr.model;

import lombok.Data;

@Data
public class DescriptionDTO {
    private String _id;
    private String title;
    private String details;
    private String user_id;
    private String collection_id;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getCollectionId() {
        return collection_id;
    }

    public void setCollectionId(String collectionId) {
        this.collection_id = collectionId;
    }
}
