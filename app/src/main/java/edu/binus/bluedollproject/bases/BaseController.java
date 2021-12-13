package edu.binus.bluedollproject.bases;

public abstract class BaseController<T> {
    protected T view;

    public BaseController(T view) {
        this.view = view;
    }
}
