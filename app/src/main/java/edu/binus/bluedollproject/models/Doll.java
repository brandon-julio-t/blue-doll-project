package edu.binus.bluedollproject.models;

import android.provider.BaseColumns;

import edu.binus.bluedollproject.bases.BaseModel;

public class Doll extends BaseModel implements BaseColumns {
    public static final String TABLE_NAME = "dolls";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_IMAGE = "image";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    private String name;
    private String description;
    private String image;
    private User user;
    private int userId;

    public Doll(int id, String name, String description, String image, int userId) {
        super(id);
        this.name = name;
        this.description = description;
        this.image = image;
        this.userId = userId;
    }

    public static String[] all() {
        return new String[]{
                COLUMN_NAME_NAME,
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_IMAGE,
        };
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
