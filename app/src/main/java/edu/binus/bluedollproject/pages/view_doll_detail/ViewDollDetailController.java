package edu.binus.bluedollproject.pages.view_doll_detail;

import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.facades.ImageResolver;
import edu.binus.bluedollproject.models.Doll;
import edu.binus.bluedollproject.models.User;
import edu.binus.bluedollproject.repositories.DollRepository;

public class ViewDollDetailController extends BaseController<ViewDollDetailActivity> {
    private final Doll doll;

    public ViewDollDetailController(ViewDollDetailActivity view) {
        super(view);

        DollRepository repo = new DollRepository(view);
        int id = view.getIntent().getExtras().getInt("id");
        doll = repo.getOneById(id);

        view.getImage().setImageResource(ImageResolver.get(doll.getImage()));
        view.getName().setText(doll.getName());
        view.getDescription().setText(doll.getDescription());
        view.getShare().setOnClickListener(this::onShare);
    }

    private void onShare(View v) {
        String phone = view.getPhoneNumber().getText().toString().trim();

        if (phone.isEmpty()) {
            Toast.makeText(view, "Phone number must not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(view, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.SEND_SMS}, 69420);
        }
    }

    public void sendSms() {
        User user = new Auth(view).getUser();
        String phone = view.getPhoneNumber().getText().toString().trim(); // 15555215554
        String message = String.format(
                "Hey, check this doll from BlueDoll! It’s the %s and it’s so awesome!\n- %s\n",
                doll.getName(),
                user.getName()
        );

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, null, null);
        Toast.makeText(view, "SMS Message sent!", Toast.LENGTH_SHORT).show();
    }
}
