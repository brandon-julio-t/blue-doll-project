package edu.binus.bluedollproject.pages.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.binus.bluedollproject.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText name;
    private EditText password;
    private EditText passwordConfirmation;
    private RadioGroup gender;
    private DatePicker dateOfBirth;
    private CheckBox agreeToTnc;
    private Button register;
    private TextView loginHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.register_email);
        name = findViewById(R.id.register_name);
        password = findViewById(R.id.register_password);
        passwordConfirmation = findViewById(R.id.register_password_confirmation);
        gender = findViewById(R.id.register_gender);
        dateOfBirth = findViewById(R.id.register_date_picker);
        agreeToTnc = findViewById(R.id.register_tnc);
        register = findViewById(R.id.register_register);
        loginHere = findViewById(R.id.register_login);

        new RegisterController(this);
    }

    public EditText getEmail() {
        return email;
    }

    public EditText getName() {
        return name;
    }

    public EditText getPassword() {
        return password;
    }

    public EditText getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public RadioGroup getGender() {
        return gender;
    }

    public DatePicker getDateOfBirth() {
        return dateOfBirth;
    }

    public CheckBox getAgreeToTnc() {
        return agreeToTnc;
    }

    public Button getRegister() {
        return register;
    }

    public TextView getLoginHere() {
        return loginHere;
    }
}