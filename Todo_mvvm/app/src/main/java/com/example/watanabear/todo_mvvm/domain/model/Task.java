package com.example.watanabear.todo_mvvm.domain.model;


import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by watanabear on 2017/03/18.
 */

public class Task extends RealmObject{

    public static final String FEILD_ID             = "id";
    public static final String FEILD_TITLE          = "title";
    public static final String FEILD_DESCRIPTION    = "description";
    public static final String FEILD_IS_COMPLETE    = "isComplete";

    @PrimaryKey
    public String   id;
    public String   title;
    public String   description;
    public Boolean  isComplete;

    public static Task create(String title, String description) {
        Task t = new Task();
        t.id = UUID.randomUUID().toString();
        t.setTitle(title);
        t.setDescription(description);
        t.setComplete(false);
        return t;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
