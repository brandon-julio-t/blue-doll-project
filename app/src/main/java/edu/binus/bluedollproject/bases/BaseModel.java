package edu.binus.bluedollproject.bases;

public abstract class BaseModel {
    protected int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
