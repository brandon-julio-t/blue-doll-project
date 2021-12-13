package edu.binus.bluedollproject.pages.insert_doll;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import edu.binus.bluedollproject.R;

public class InsertDollActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private Spinner image;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doll);

        name = findViewById(R.id.form_name);
        description = findViewById(R.id.form_description);
        image = findViewById(R.id.form_image);
        save = findViewById(R.id.form_save);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"-- choose image --", "reimu", "marisa", "kanna"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        image.setAdapter(adapter);

        new InsertDollController(this);
    }

    public EditText getName() {
        return name;
    }

    public EditText getDescription() {
        return description;
    }

    public Spinner getImage() {
        return image;
    }

    public Button getSave() {
        return save;
    }
}