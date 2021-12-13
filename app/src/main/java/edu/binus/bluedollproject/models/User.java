package edu.binus.bluedollproject.models;

import android.provider.BaseColumns;

import edu.binus.bluedollproject.bases.BaseModel;

public class User extends BaseModel implements BaseColumns {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_GENDER = "gender";
    public static final String COLUMN_NAME_ROLE = "role";
    private String name;
    private String email;
    private String password;
    private String gender;
    private String role;

    public User(int id, String name, String email, String password, String gender, String role) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public static String[] all() {
        return new String[]{
                COLUMN_NAME_NAME,
                COLUMN_NAME_EMAIL,
                COLUMN_NAME_PASSWORD,
                COLUMN_NAME_GENDER,
                COLUMN_NAME_ROLE,
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
