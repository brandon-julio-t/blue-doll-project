package edu.binus.bluedollproject.bases;

import android.content.Context;

import edu.binus.bluedollproject.models.User;

public abstract class BaseRepository {
    protected Context context;

    public BaseRepository(Context context) {
        this.context = context;
    }
}
