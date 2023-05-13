package com.example.descriptionr.model;

import lombok.Data;

@Data
public class DescriptionDTO {
    private String title;
    private String details;
    private String user_id;
    private String collection_id;

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
