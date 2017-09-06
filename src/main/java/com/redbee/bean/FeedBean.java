package com.redbee.bean;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by goroma on 24/08/2017.
 */
public class FeedBean {

    @Id
    private ObjectId _id;

    private String email;
    private String message;
    private String created_time;
    private String id;
    private String story;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
