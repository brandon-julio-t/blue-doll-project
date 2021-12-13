package edu.binus.bluedollproject.pages.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.binus.bluedollproject.R;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private TextView registerHere;

    public EditText getEmail() {
        return email;
    }

    public EditText getPassword() {
        return password;
    }

    public Button getLogin() {
        return login;
    }

    public TextView getRegisterHere() {
        return registerHere;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_login);
        registerHere = findViewById(R.id.login_register_here);

        new LoginController(this);
    }
}