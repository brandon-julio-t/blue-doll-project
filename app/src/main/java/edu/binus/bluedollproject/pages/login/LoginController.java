package edu.binus.bluedollproject.pages.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.pages.register.RegisterActivity;
import edu.binus.bluedollproject.pages.view_all_dolls.ViewAllDollsActivity;

public class LoginController extends BaseController<LoginActivity> {
    public LoginController(LoginActivity view) {
        super(view);
        view.getLogin().setOnClickListener(this::onLogin);
        view.getRegisterHere().setOnClickListener(this::onRegisterHere);
    }

    private void onLogin(View v) {
        String email = view.getEmail().getText().toString().trim();
        String password = view.getPassword().getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(view, "Email must not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(view, "Password must not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Auth svc = new Auth(view);
        if (svc.login(email, password)) {
            view.startActivity(new Intent(view, ViewAllDollsActivity.class));
            view.finish();
            return;
        }

        Toast.makeText(view, "Invalid credentials!", Toast.LENGTH_SHORT).show();
    }

    private void onRegisterHere(View v) {
        view.startActivity(new Intent(view, RegisterActivity.class));
    }
}
