package edu.binus.bluedollproject.pages.view_doll_detail;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.binus.bluedollproject.R;

public class ViewDollDetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView description;
    private EditText phoneNumber;
    private Button share;
    private ViewDollDetailController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doll_detail);

        image = findViewById(R.id.vdd_image);
        name = findViewById(R.id.vdd_name);
        description = findViewById(R.id.vdd_description);
        phoneNumber = findViewById(R.id.vdd_phone);
        share = findViewById(R.id.vdd_share);

        controller = new ViewDollDetailController(this);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 69420) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                controller.sendSms();
            } else {
                Toast.makeText(this, "Failed to send SMS.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getName() {
        return name;
    }

    public TextView getDescription() {
        return description;
    }

    public EditText getPhoneNumber() {
        return phoneNumber;
    }

    public Button getShare() {
        return share;
    }
}