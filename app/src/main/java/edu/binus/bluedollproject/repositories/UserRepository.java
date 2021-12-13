package edu.binus.bluedollproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import edu.binus.bluedollproject.bases.BaseRepository;
import edu.binus.bluedollproject.data_accesses.UserDA;
import edu.binus.bluedollproject.models.User;

public class UserRepository extends BaseRepository {
    public UserRepository(Context context) {
        super(context);
    }

    public List<User> getAll() {
        Cursor cursor = new UserDA(context).getReadableDatabase().query(
                User.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        List<User> models = new ArrayList<>();
        while (cursor.moveToNext()) {
            models.add(
                    new User(
                            cursor.getInt(cursor.getColumnIndexOrThrow(User._ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_NAME_NAME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_NAME_EMAIL)),
                            cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_NAME_PASSWORD)),
                            cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_NAME_GENDER)),
                            cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_NAME_ROLE))
                    )
            );
        }
        cursor.close();
        return models;
    }

    public User getOneById(int userId) {
        for (User user : getAll())
            if (user.getId() == userId)
                return user;
        return null;
    }

    public User getOneByEmail(String email) {
        for (User user : getAll())
            if (user.getEmail().equals(email))
                return user;
        return null;
    }

    public boolean create(User user) {
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME_NAME, user.getName());
        values.put(User.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(User.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(User.COLUMN_NAME_GENDER, user.getGender());
        values.put(User.COLUMN_NAME_ROLE, user.getRole());
        return new UserDA(context).getWritableDatabase()
                .insert(User.TABLE_NAME, null, values) != -1;
    }
}
