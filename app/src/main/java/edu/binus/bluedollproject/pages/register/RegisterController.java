package edu.binus.bluedollproject.pages.register;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import edu.binus.bluedollproject.R;
import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.models.User;
import edu.binus.bluedollproject.pages.login.LoginActivity;
import edu.binus.bluedollproject.repositories.UserRepository;

public class RegisterController extends BaseController<RegisterActivity> {
    public RegisterController(RegisterActivity view) {
        super(view);
        view.getRegister().setOnClickListener(this::onRegister);
        view.getLoginHere().setOnClickListener(this::onLoginHere);
    }

    private void onRegister(View v) {
        String email = view.getEmail().getText().toString().trim();
        String name = view.getName().getText().toString().trim();
        String password = view.getPassword().getText().toString().trim();
        String passwordConfirmation = view.getPasswordConfirmation().getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(view, "Email must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@")) {
            Toast.makeText(view, "Email format is invalid!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(view, "Name must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(view, "Password must be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(view, "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            Toast.makeText(view, "Password does not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedGender = view.getGender().getCheckedRadioButtonId();
        if (selectedGender == -1) {
            Toast.makeText(view, "Gender must be selected!", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender = selectedGender == R.id.register_gender_male ? "male" : "female";

        if (!view.getAgreeToTnc().isChecked()) {
            Toast.makeText(view, "You must agree to T&C!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository repo = new UserRepository(view);
        User existingUser = repo.getOneByEmail(email);
        if (existingUser != null) {
            Toast.makeText(view, "User already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        List<User> users = repo.getAll();
        String nextId = String.format("US%03d", users.size() + 1);
        User user = new User(
                users.size() + 1,
                name,
                email,
                password,
                gender,
                "member"
        );

        if (!repo.create(user)) {
            Toast.makeText(view, "An error occurred.", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(view, "Register success!", Toast.LENGTH_SHORT).show();
        view.startActivity(new Intent(view, LoginActivity.class));
        view.finish();
    }

    private void onLoginHere(View v) {
        view.startActivity(new Intent(view, LoginActivity.class));
    }
}
