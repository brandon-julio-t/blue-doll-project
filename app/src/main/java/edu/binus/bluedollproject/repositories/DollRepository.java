package edu.binus.bluedollproject.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import edu.binus.bluedollproject.bases.BaseRepository;
import edu.binus.bluedollproject.data_accesses.DollDA;
import edu.binus.bluedollproject.models.Doll;

public class DollRepository extends BaseRepository {
    public DollRepository(Context context) {
        super(context);
    }

    public List<Doll> getAll() {
        Cursor cursor = new DollDA(context).getReadableDatabase().query(
                Doll.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Doll> models = new ArrayList<>();
        UserRepository repo = new UserRepository(context);
        while (cursor.moveToNext()) {
            Doll model = new Doll(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Doll._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Doll.COLUMN_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Doll.COLUMN_NAME_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Doll.COLUMN_NAME_IMAGE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(Doll.COLUMN_NAME_USER_ID))
            );
            model.setUser(repo.getOneById(model.getUserId()));
            models.add(model);
        }

        cursor.close();
        return models;
    }

    public Doll getOneById(int id) {
        for (Doll doll : getAll())
            if (doll.getId() == id)
                return doll;
        return null;
    }

    public Doll getOneByName(String name) {
        for (Doll doll : getAll())
            if (doll.getName().equals(name))
                return doll;
        return null;
    }

    public boolean delete(Doll doll) {
        return new DollDA(context).getWritableDatabase().delete(
                Doll.TABLE_NAME,
                Doll._ID + " = ?",
                new String[]{String.valueOf(doll.getId())}
        ) > 0;
    }

    public boolean create(Doll doll) {
        ContentValues values = new ContentValues();
        values.put(Doll.COLUMN_NAME_NAME, doll.getName());
        values.put(Doll.COLUMN_NAME_DESCRIPTION, doll.getDescription());
        values.put(Doll.COLUMN_NAME_IMAGE, doll.getImage());
        values.put(Doll.COLUMN_NAME_USER_ID, doll.getUserId());
        return new DollDA(context).getWritableDatabase()
                .insert(Doll.TABLE_NAME, null, values) != -1;
    }

    public boolean update(Doll doll) {
        ContentValues values = new ContentValues();
        values.put(Doll.COLUMN_NAME_NAME, doll.getName());
        values.put(Doll.COLUMN_NAME_DESCRIPTION, doll.getDescription());
        values.put(Doll.COLUMN_NAME_IMAGE, doll.getImage());
        values.put(Doll.COLUMN_NAME_USER_ID, doll.getUserId());
        return new DollDA(context).getWritableDatabase()
                .update(
                        Doll.TABLE_NAME,
                        values,
                        Doll._ID + " =  ?",
                        new String[]{String.valueOf(doll.getId())}
                ) != -1;
    }
}
