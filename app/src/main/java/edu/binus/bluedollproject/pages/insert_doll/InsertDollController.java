package edu.binus.bluedollproject.pages.insert_doll;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.models.Doll;
import edu.binus.bluedollproject.models.User;
import edu.binus.bluedollproject.repositories.DollRepository;

public class InsertDollController extends BaseController<InsertDollActivity> {
    private final DollRepository repo;

    public InsertDollController(InsertDollActivity view) {
        super(view);

        repo = new DollRepository(view);

        Bundle extras = view.getIntent().getExtras();
        String action = extras != null && extras.isEmpty() ? "create" : "update";

        if (extras != null && action.equals("update")) {
            int id = extras.getInt("id");
            Doll doll = repo.getOneById(id);
            view.getName().setText(doll.getName());
            view.getDescription().setText(doll.getDescription());
            view.getImage().setSelection(getIdxByImgName(doll.getImage()));
            view.getSave().setOnClickListener(this.onUpdate(doll));
        } else {
            view.getSave().setOnClickListener(this::onCreate);
        }
    }

    private View.OnClickListener onUpdate(Doll doll) {
        return v -> {
            String name = view.getName().getText().toString().trim();
            String description = view.getDescription().getText().toString().trim();
            String image = view.getImage().getSelectedItem().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(view, "Name must be filled!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (repo.getOneByName(name) != null) {
                Toast.makeText(view, "Name already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (description.isEmpty()) {
                Toast.makeText(view, "Description must be filled!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (image.isEmpty() || image.equals("-- choose image -- ")) {
                Toast.makeText(view, "Image must be chosen!", Toast.LENGTH_SHORT).show();
                return;
            }

            doll.setName(name);
            doll.setDescription(description);
            doll.setImage(image);
            boolean isSuccess = repo.update(doll);

            Toast.makeText(
                    view,
                    isSuccess
                            ? "Doll updated successfully!"
                            : "An error occurred while updating doll.",
                    Toast.LENGTH_SHORT
            ).show();
        };
    }

    private void onCreate(View v) {
        String name = view.getName().getText().toString().trim();
        String description = view.getDescription().getText().toString().trim();
        String image = view.getImage().getSelectedItem().toString().trim();
        User user = new Auth(view).getUser();

        if (name.isEmpty()) {
            Toast.makeText(view, "Name must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (repo.getOneByName(name) != null) {
            Toast.makeText(view, "Name already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(view, "Description must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (image.isEmpty() || image.equals("-- choose image --")) {
            Toast.makeText(view, "Image must be chosen!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess = repo.create(
                new Doll(
                        0,
                        name,
                        description,
                        image,
                        user.getId()
                )
        );

        Toast.makeText(
                view,
                isSuccess
                        ? "Doll created successfully!"
                        : "An error occurred while creating doll.",
                Toast.LENGTH_SHORT
        ).show();
    }

    private int getIdxByImgName(String name) {
        String[] arr = new String[]{"-- choose image --", "reimu", "marisa", "kanna"};
        for (int i = 0; i < arr.length; i++)
            if (arr[i].equals(name))
                return i;
        return -1;
    }
}
